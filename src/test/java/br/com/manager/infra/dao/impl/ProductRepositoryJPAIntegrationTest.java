package br.com.manager.infra.dao.impl;

import br.com.manager.infra.dao.IProductRepository;
import br.com.manager.model.entity.Product;
import br.com.manager.test.ProductManagerTestUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional
public class ProductRepositoryJPAIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private IProductRepository productRepository;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive webArchive = ProductManagerTestUtils.createWebArchiveBase(Boolean.TRUE);
        ProductManagerTestUtils.addClass(webArchive, ProductRepositoryJPA.class);
        return webArchive;
    }

    @Before
    public void before() {
        entityManager.createQuery("delete from product").executeUpdate();
    }

    @Test
    public void testListAll() throws Exception {
        Product product = new Product();
        product.setName("nome_test");
        product.setValue(BigDecimal.valueOf(150.2));
        entityManager.persist(product);

        List<Product> products = productRepository.listAll(null);
        assertEquals("Nao foi retornada a quantidade de itens existentes", 1, products.size());
        assertEquals("Item nao foi retornado com nome correto", "nome_test", products.get(0).getName());
        assertEquals("Item nao foi retornado com value correto", BigDecimal.valueOf(150.2), products.get(0).getValue());
    }

    @Test
    public void testListAllOrdered() throws Exception {
        for (int i = 2; i >= 0; i--) {
            Product product = new Product();
            product.setName("nome_test_" + i);
            product.setValue(BigDecimal.valueOf(i));
            entityManager.persist(product);
        }

        List<Product> products = productRepository.listAll(null);
        assertEquals("Nao foi retornada a quantidade de itens existentes", 3, products.size());
        for (int i = 0; i < 3; i++) {
            assertEquals("Item " + i + " nao retornou ordenado", "nome_test_" + i, products.get(i).getName());
        }
    }

}