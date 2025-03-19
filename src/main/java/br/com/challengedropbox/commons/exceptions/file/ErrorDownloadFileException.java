package br.com.challengedropbox.commons.exceptions.file;

import br.com.challengedropbox.commons.enums.ErrorException;
import br.com.challengedropbox.commons.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class ErrorDownloadFileException extends AppException {

    public ErrorDownloadFileException() {
        super(ErrorException.VALIDATION_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

}
