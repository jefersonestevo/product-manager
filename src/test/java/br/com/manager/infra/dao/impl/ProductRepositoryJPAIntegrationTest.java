package br.com.manager.infra.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.manager.infra.dao.IProductRepository;
import br.com.manager.model.entity.Product;
import br.com.manager.test.ProductManagerTestUtils;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional
public class ProductRepositoryJPAIntegrationTest {

    @Inject
    private IProductRepository productRepository;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive webArchive = ProductManagerTestUtils.createWebArchiveBase(Boolean.TRUE);
        ProductManagerTestUtils.addClass(webArchive, ProductRepositoryJPA.class);
        return webArchive;
    }

    @Test
    @UsingDataSet("dataset/product_simple.xml")
    public void testListAll() throws Exception {
        List<Product> products = productRepository.listAll(null);
        assertEquals("Nao foi retornada a quantidade de itens existentes", 1, products.size());
        assertEquals("Item nao foi retornado com nome correto", Long.valueOf(1l), products.get(0).getId());
        assertEquals("Item nao foi retornado com nome correto", "Product_Test_1", products.get(0).getName());
        assertEquals("Item nao foi retornado com value correto", BigDecimal.valueOf(123.23), products.get(0).getValue());
    }

    @Test
    @UsingDataSet("dataset/product_list.xml")
    public void testListAll_ShouldReturnOrdered() throws Exception {
        List<Product> products = productRepository.listAll(null);
        assertEquals("Nao foi retornada a quantidade de itens existentes", 3, products.size());
        for (int i = 1; i <= 3; i++) {
            assertEquals("Item " + i + " nao retornou ordenado", Long.valueOf(i), products.get(i-1).getId());
        }
    }

}