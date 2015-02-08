package br.com.manager.infra.exception;

public enum ExceptionCode {

    // Erros 600000000+ p/ Acesso ao BD
    DB_ENTITY_NOT_FOUND(600000001), //
    DB_RESULT_NOT_FOUND(600000002), //
    DB_UNIQUE_VIOLATION(600000003), //
    DB_TIMEOUT(600000004), //
    DB_DUPLICATED_ENTITY(600000005), //
    DB_DATABASE_ACCESS_ERROR(600000006), //
    DB_CONSTRAINT_ERROR(600001001), //

    // Erros 900000000+ p/ erros inesperados
    UNKNOWN_ERROR(900000001) //
    ;

    private Integer code;

    private ExceptionCode(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String toString() {
        return getCode() + " > " + name();
    }
}
