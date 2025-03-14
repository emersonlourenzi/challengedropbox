package br.com.challengedropbox.repository.file.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "file")
public class FileEntity {

    @Id
    private String id;
    private String nameFile;
    private String typeFile;
    private Long sizeFile;
    private String idUser;
    @Builder.Default
    private LocalDateTime dateUpload = LocalDateTime.now();

}
