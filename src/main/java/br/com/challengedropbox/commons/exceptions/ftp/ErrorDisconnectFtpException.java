package br.com.challengedropbox.commons.exceptions.ftp;

import br.com.challengedropbox.commons.enums.ErrorException;
import br.com.challengedropbox.commons.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class ErrorDisconnectFtpException extends AppException {

    public ErrorDisconnectFtpException() {
        super(ErrorException.ERROR_CONECT_SERVER_FTP, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

}
