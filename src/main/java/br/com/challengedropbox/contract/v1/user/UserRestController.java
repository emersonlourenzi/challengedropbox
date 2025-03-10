package br.com.challengedropbox.contract.v1.user;

import br.com.challengedropbox.model.user.request.UserRequest;
import br.com.challengedropbox.model.user.response.UserResponse;
import br.com.challengedropbox.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/users")
@Tag(name = "User")
public class UserRestController {

    private final UserService userService;

    @PostMapping
    @Operation(description = "Criar usuário")
    public UserResponse createUser(@RequestBody @Valid UserRequest user) {
        return userService.createUser(user);
    }

    @GetMapping
    @Operation(description = "Busca usuário por email")
    public UserResponse findByEmail(@RequestParam @Email(message = "Email inválido") String email) {
        return userService.findByEmail(email);
    }

}
