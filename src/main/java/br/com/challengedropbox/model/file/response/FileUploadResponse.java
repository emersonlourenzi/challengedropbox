package br.com.challengedropbox.model.file.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponse {

    private String id;
    private String nameFile;
    private String typeFile;
    private Long sizeFile;
    private String idUser;
    private LocalDateTime dateUpload;

}
