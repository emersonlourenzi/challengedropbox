package br.com.challengedropbox.commons.exceptions.user;

import br.com.challengedropbox.commons.enums.ErrorException;
import br.com.challengedropbox.commons.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class ErrorUserExists extends AppException {

    public ErrorUserExists() {
        super(ErrorException.EMAIL_USER_EXISTS, HttpStatus.CONFLICT, null);
    }

}
