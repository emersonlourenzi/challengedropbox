package br.com.challengedropbox.model.file.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDeleteRequest {

    @NotBlank(message = "Obrigatório informar id do usuário")
    private String idUser;
    @NotEmpty(message = "Obrigatório infomar id dos arquivos")
    private List<String> nameFiles;

}
