package br.com.challengedropbox.mapper.file;

import br.com.challengedropbox.model.file.response.FileUploadResponse;
import br.com.challengedropbox.repository.file.entity.FileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileEntityToUploadResponseMapper {

    static FileUploadResponse toFileUploadResponse(FileEntity fileEntity) {
        return Mappers.getMapper(FileEntityToUploadResponseMapper.class)
            .mapper(fileEntity);
    }

    FileUploadResponse mapper(FileEntity fileEntity);

}
