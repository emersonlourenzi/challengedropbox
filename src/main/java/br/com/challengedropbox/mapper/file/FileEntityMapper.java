package br.com.challengedropbox.mapper.file;

import br.com.challengedropbox.repository.file.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public class FileEntityMapper {

    public static FileEntity toFileEntityMapper(MultipartFile file, String fileName, String idUser) {
        return FileEntity.builder()
            .nameFile(fileName)
            .typeFile(getType(Objects.requireNonNull(file.getOriginalFilename())))
            .sizeFile(file.getSize())
            .idUser(idUser)
            .build();
    }

    static String getType(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
