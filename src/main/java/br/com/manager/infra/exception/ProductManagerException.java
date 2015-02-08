package br.com.manager.infra.exception;

public class ProductManagerException extends RuntimeException {
    private ExceptionCode code;

    public ProductManagerException(ExceptionCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public ExceptionCode getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.getClass()+">>code="+getCode()+"\n"+super.toString();
    }
}
