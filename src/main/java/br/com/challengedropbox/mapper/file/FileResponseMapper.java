package br.com.challengedropbox.mapper.file;

import br.com.challengedropbox.model.file.response.FileResponse;
import org.apache.commons.net.ftp.FTPFile;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FileResponseMapper {

    FileResponseMapper INSTANCE = Mappers.getMapper(FileResponseMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "size", source = "size")
    @Mapping(target = "type", expression = "java(FileEntityMapper.getType(file.getName()))")
    FileResponse toFileResponse(FTPFile file);

    List<FileResponse> toFileResponseList(FTPFile[] files);

    @AfterMapping
    default void filterInvalidFiles(@MappingTarget List<FileResponse> fileResponses) {
        fileResponses.removeIf(file -> file.getName().equals(".") || file.getName().equals(".."));
    }

}
