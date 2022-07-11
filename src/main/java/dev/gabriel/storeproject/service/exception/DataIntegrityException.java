package dev.gabriel.storeproject.service.exception;

public class DataIntegrityException extends RuntimeException{
    public DataIntegrityException(String msg) {
        super(msg);
    }

    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
