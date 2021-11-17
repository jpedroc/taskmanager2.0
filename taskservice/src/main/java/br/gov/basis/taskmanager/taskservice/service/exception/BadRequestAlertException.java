package br.gov.basis.taskmanager.taskservice.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestAlertException extends RuntimeException {

    public BadRequestAlertException(final String mensagem) {
        this(mensagem, null);
    }

    public BadRequestAlertException(final String message, final Throwable motivo) {
        super(message, motivo);
    }
}
