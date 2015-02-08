package br.com.manager.infra.exception;

public class ProductManagerDatabaseException extends ProductManagerException {
    public ProductManagerDatabaseException(ExceptionCode code, Throwable cause) {
        super(code, cause);
    }
}
