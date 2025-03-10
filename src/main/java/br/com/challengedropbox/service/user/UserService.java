package br.com.challengedropbox.service.user;

import br.com.challengedropbox.commons.exceptions.user.ErrorSaveUserException;
import br.com.challengedropbox.commons.exceptions.user.ErrorUserExists;
import br.com.challengedropbox.commons.exceptions.user.ErrorUserNotExists;
import br.com.challengedropbox.mapper.user.UserEntityToResponseMapper;
import br.com.challengedropbox.mapper.user.UserRequestToEntityMapper;
import br.com.challengedropbox.model.user.request.UserRequest;
import br.com.challengedropbox.model.user.response.UserResponse;
import br.com.challengedropbox.repository.user.UserRepository;
import br.com.challengedropbox.repository.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest user) {
        return Optional.ofNullable(user)
            .map(this::validateExistsUser)
            .map(this::criptographyPassword)
            .map(UserRequestToEntityMapper::toUserEntity)
            .map(userRepository::save)
            .map(UserEntityToResponseMapper::toResponse)
            .orElseThrow(ErrorSaveUserException::new);
    }

    private UserRequest validateExistsUser(UserRequest user) {
        var existsUser = userRepository.findByEmail(user.getEmail());
        if (!ObjectUtils.isEmpty(existsUser)) {
            throw new ErrorUserExists();
        }
        return user;
    }

    private UserRequest criptographyPassword(UserRequest user) {
        user.setPassword(encryptPassword(user.getPassword()));
        return user;
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new ErrorSaveUserException();
        }
    }

    public UserResponse findByEmail(String email) {
        return Optional.ofNullable(email)
            .map(this::validadeUserNotExists)
            .map(UserEntityToResponseMapper::toResponse)
            .orElseThrow();
    }

    private UserEntity validadeUserNotExists(String email) {
        var existsUser = userRepository.findByEmail(email);
        if (ObjectUtils.isEmpty(existsUser)) {
            throw new ErrorUserNotExists();
        }
        return existsUser;
    }

}
