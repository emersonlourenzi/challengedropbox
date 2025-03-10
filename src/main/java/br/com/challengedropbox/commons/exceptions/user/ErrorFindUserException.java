package br.com.challengedropbox.commons.exceptions.user;

import br.com.challengedropbox.commons.enums.ErrorException;
import br.com.challengedropbox.commons.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class ErrorFindUserException extends AppException {

    public ErrorFindUserException() {
        super(ErrorException.ERROR_FIND_USER, HttpStatus.CONFLICT, null);
    }

}
