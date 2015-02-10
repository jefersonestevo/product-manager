package br.com.manager.infra.dao.utils;

import br.com.manager.model.entity.Product;
import br.com.manager.test.AbstractUnitTest;
import org.hibernate.criterion.CriteriaQuery;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BaseDAOJPATest extends AbstractUnitTest {

    private BaseDAOJPA<Product, Long> daoJPA;

    private DatabaseTemplateJPA template;

    @Override
    public void init() throws Exception {
        template = mock(DatabaseTemplateJPA.class);

        daoJPA = new BaseDAOJPAImplTest(template);
    }

    @Test
    public void testFind() throws Exception {
        daoJPA.find(1l);

        verify(template).find(eq(daoJPA.getEntityClass()), eq(1l));
    }

    @Test
    public void testList() throws Exception {
        daoJPA.list();

        verify(template).list(eq(daoJPA.getEntityClass()));
    }

    @Test
    public void testInsert() throws Exception {
        Product product = new Product();
        daoJPA.insert(product);

        verify(template).insert(eq(product));
    }

    @Test
    public void testUpdate() throws Exception {
        Product product = new Product();
        daoJPA.update(product);

        verify(template).update(eq(product));
    }

    private class BaseDAOJPAImplTest extends BaseDAOJPA<Product, Long> {
        private DatabaseTemplateJPA template;

        private BaseDAOJPAImplTest(DatabaseTemplateJPA template) {
            this.template = template;
        }

        @Override
        protected Class<Product> getEntityClass() {
            return Product.class;
        }

        @Override
        public DatabaseTemplateJPA getTemplate() {
            return template;
        }
    }
}