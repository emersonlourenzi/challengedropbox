package br.com.challengedropbox.mapper.user;

import br.com.challengedropbox.model.user.response.UserResponse;
import br.com.challengedropbox.repository.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ListUserEntityToResponseMapper {

    static List<UserResponse> toListResponse(List<UserEntity> userEntity) {
        return Mappers.getMapper(ListUserEntityToResponseMapper.class)
            .mapper(userEntity);
    }

    List<UserResponse> mapper(List<UserEntity> userEntity);

}
