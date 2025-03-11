package br.com.challengedropbox.repository.user;

import br.com.challengedropbox.repository.user.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity findByEmail(String email);

    void deleteByEmail(@Valid String email);

}
