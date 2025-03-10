package br.com.challengedropbox.model.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "Campo nome é obrigatório")
    private String name;
    @Email(message = "Informe o email em formato válido")
    @NotBlank(message = "Campo email é obrigatório")
    private String email;
    @NotBlank(message = "Campo senha é obrigatório")
    private String password;

}
