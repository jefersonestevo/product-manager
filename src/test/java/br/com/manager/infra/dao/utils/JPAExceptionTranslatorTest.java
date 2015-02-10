package br.com.manager.infra.dao.utils;

import br.com.manager.infra.exception.ExceptionCode;
import br.com.manager.infra.exception.ProductManagerDatabaseException;
import br.com.manager.test.AbstractUnitTest;
import org.junit.Test;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.HashSet;

import static org.junit.Assert.*;

public class JPAExceptionTranslatorTest extends AbstractUnitTest {

    @Override
    public void init() throws Exception {
    }

    @Test
    public void testGetTranslatedException_EntityNotFoundException() throws Exception {
        this.validateExceptionWithExceptionCode(new EntityNotFoundException(), ExceptionCode.DB_ENTITY_NOT_FOUND);
    }

    @Test
    public void testGetTranslatedException_NoResultException() throws Exception {
        this.validateExceptionWithExceptionCode(new NoResultException(), ExceptionCode.DB_RESULT_NOT_FOUND);
    }

    @Test
    public void testGetTranslatedException_NonUniqueResultException() throws Exception {
        this.validateExceptionWithExceptionCode(new NonUniqueResultException(), ExceptionCode.DB_UNIQUE_VIOLATION);
    }

    @Test
    public void testGetTranslatedException_QueryTimeoutException() throws Exception {
        this.validateExceptionWithExceptionCode(new QueryTimeoutException(), ExceptionCode.DB_TIMEOUT);
    }

    @Test
    public void testGetTranslatedException_EntityExistsException() throws Exception {
        this.validateExceptionWithExceptionCode(new EntityExistsException(), ExceptionCode.DB_DUPLICATED_ENTITY);
    }

    @Test
    public void testGetTranslatedException_PersistenceException() throws Exception {
        this.validateExceptionWithExceptionCode(new PersistenceException(), ExceptionCode.DB_DATABASE_ACCESS_ERROR);
    }

    @Test
    public void testGetTranslatedException_ConstraintViolationException() throws Exception {
        this.validateExceptionWithExceptionCode(new ConstraintViolationException(new HashSet<ConstraintViolation<?>>()), ExceptionCode.DB_CONSTRAINT_ERROR);
    }

    private void validateExceptionWithExceptionCode(RuntimeException e, ExceptionCode code) {
        ProductManagerDatabaseException pe = JPAExceptionTranslator.getTranslatedException(e);
        assertNotNull("Should never return null", pe);
        assertEquals("The returned code on ProductManagerDatabaseException is not the expected", code, pe.getCode());
    }
}