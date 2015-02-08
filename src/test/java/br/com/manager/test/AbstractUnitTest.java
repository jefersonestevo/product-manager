package br.com.manager.test;

import org.apache.commons.lang.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Ignore;

@Ignore
public abstract class AbstractUnitTest {

    @Before
    public abstract void init() throws Exception;

    protected void setField(Object target, String fieldName, Object value) throws java.lang.IllegalAccessException {
        FieldUtils.writeField(target, fieldName, value, Boolean.TRUE);
    }

}
