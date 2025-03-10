package br.com.challengedropbox.commons.exceptions.user;

import br.com.challengedropbox.commons.enums.ErrorException;
import br.com.challengedropbox.commons.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class ErrorUserNotExists extends AppException {

    public ErrorUserNotExists() {
        super(ErrorException.USER_NOT_EXISTS, HttpStatus.NOT_FOUND, null);
    }

}
