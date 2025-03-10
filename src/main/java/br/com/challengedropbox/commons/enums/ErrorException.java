package br.com.challengedropbox.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.MessageFormat;

@Getter
@AllArgsConstructor
public enum ErrorException {

    ERROR_UNKNOWN("1", "Erro desconhecido"),
    ERROR_FIND_USER("2", "Erro na busca ao usuário"),
    ERROR_SAVE_USER("3", "Erro ao salvar usuário"),
    USER_NOT_FOUND("4", "Usuário não encontrado"),
    EMAIL_USER_EXISTS("5", "Email informado já cadastrado"),
    VALIDATION_ERROR("6", "Erro na válidação dos dados do usuário"),
    USER_NOT_EXISTS("7", "Usuário informado não encontrado ou não existe"),
    ;

    private final String code;
    private final String message;

    public String format(Object... args) {
        return MessageFormat.format(this.message, args);
    }

}
