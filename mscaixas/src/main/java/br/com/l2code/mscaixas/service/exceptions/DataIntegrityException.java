package br.com.l2code.mscaixas.service.exceptions;

import java.io.Serial;

public class DataIntegrityException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DataIntegrityException(String msg) {
        super(msg);
    }

    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }

}