package br.com.challengedropbox.commons.exceptions.file;

import br.com.challengedropbox.commons.enums.ErrorException;
import br.com.challengedropbox.commons.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class ErrorSaveFileException extends AppException {

    public ErrorSaveFileException() {
        super(ErrorException.ERROR_SAVE_FILE, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

}
