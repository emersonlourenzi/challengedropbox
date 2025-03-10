package br.com.challengedropbox.commons.exceptions.user;

import br.com.challengedropbox.commons.enums.ErrorException;
import br.com.challengedropbox.commons.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class ErrorUserNotFoundException extends AppException {

    public ErrorUserNotFoundException() {
        super(ErrorException.USER_NOT_FOUND, HttpStatus.NOT_FOUND, null);
    }

}
