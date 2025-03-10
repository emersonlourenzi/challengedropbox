package br.com.challengedropbox.mapper.user;

import br.com.challengedropbox.model.user.response.UserResponse;
import br.com.challengedropbox.repository.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityToResponseMapper {

    static UserResponse toResponse(UserEntity userEntity) {
        return Mappers.getMapper(UserEntityToResponseMapper.class)
                .mapper(userEntity);
    }

    UserResponse mapper(UserEntity userEntity);

}
