package br.com.manager.infra.dao.utils;

import br.com.manager.infra.exception.ExceptionCode;
import br.com.manager.infra.exception.ProductManagerDatabaseException;

import javax.persistence.*;
import javax.validation.ConstraintViolationException;

public class JPAExceptionTranslator {
    public static ProductManagerDatabaseException getTranslatedException(RuntimeException r) {

        ExceptionCode code = ExceptionCode.UNKNOWN_ERROR;

        if (r instanceof EntityNotFoundException) {
            code = ExceptionCode.DB_ENTITY_NOT_FOUND;
        } else if (r instanceof NoResultException) {
            code = ExceptionCode.DB_RESULT_NOT_FOUND;
        } else if (r instanceof NonUniqueResultException) {
            code = ExceptionCode.DB_UNIQUE_VIOLATION;
        } else if (r instanceof QueryTimeoutException) {
            code = ExceptionCode.DB_TIMEOUT;
        } else if (r instanceof EntityExistsException) {
            code = ExceptionCode.DB_DUPLICATED_ENTITY;
        } else if (r instanceof PersistenceException) {
            code = ExceptionCode.DB_DATABASE_ACCESS_ERROR;
        } else if (r instanceof ConstraintViolationException) {
            code = ExceptionCode.DB_CONSTRAINT_ERROR;
        }
        return new ProductManagerDatabaseException(code, r);
    }
}
