package br.com.challengedropbox.commons.exceptions.file;

import br.com.challengedropbox.commons.enums.ErrorException;
import br.com.challengedropbox.commons.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class ErrorDeleteFileException extends AppException {

    public ErrorDeleteFileException(String nameFile) {
        super(ErrorException.ERROR_DELETE_FILE, HttpStatus.INTERNAL_SERVER_ERROR, null, nameFile);
    }

}
