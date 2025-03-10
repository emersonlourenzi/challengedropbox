package br.com.challengedropbox.mapper.user;

import br.com.challengedropbox.model.user.request.UserRequest;
import br.com.challengedropbox.repository.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRequestToEntityMapper {

    static UserEntity toUserEntity(UserRequest userRequest) {
        return Mappers.getMapper(UserRequestToEntityMapper.class)
                .mapper(userRequest);
    }

    UserEntity mapper(UserRequest userRequest);

}
