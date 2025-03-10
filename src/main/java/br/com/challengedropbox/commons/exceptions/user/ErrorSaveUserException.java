package br.com.challengedropbox.commons.exceptions.user;

import br.com.challengedropbox.commons.enums.ErrorException;
import br.com.challengedropbox.commons.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class ErrorSaveUserException extends AppException {

    public ErrorSaveUserException() {
        super(ErrorException.ERROR_SAVE_USER, HttpStatus.CONFLICT, null);
    }

}
