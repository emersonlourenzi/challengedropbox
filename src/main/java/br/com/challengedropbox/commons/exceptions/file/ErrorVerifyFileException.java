package br.com.challengedropbox.commons.exceptions.file;

import br.com.challengedropbox.commons.enums.ErrorException;
import br.com.challengedropbox.commons.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class ErrorVerifyFileException extends AppException {

    public ErrorVerifyFileException() {
        super(ErrorException.ERROR_VERIFY_FILE, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

}
