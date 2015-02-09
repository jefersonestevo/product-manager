package br.com.manager.infra.dao.utils;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Transactional;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.manager.model.entity.BaseEntity;
import br.com.manager.model.entity.Product;
import br.com.manager.test.ProductManagerTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
@Transactional
public class DatabaseTemplateJPATest {

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive webArchive = ProductManagerTestUtils.createWebArchiveBase(Boolean.TRUE);
        ProductManagerTestUtils.addClass(webArchive, DatabaseTemplateJPA.class);
        return webArchive;
    }

    @Inject
    private DatabaseTemplateJPA templateJPA;

    @Test
    @UsingDataSet("dataset/empty.xml")
    public void testInsert() throws Exception {
        templateJPA.insert(this.createEntity(null, "Product_Name_1"));

        List<Product> products = templateJPA.list(Product.class);
        assertEquals("Não foi inserido o produto", 1, products.size());
        assertNotNull("Deveria ser gerado Id para o produto", products.get(0).getId());
        assertEquals("Não foi inserido o nome do produto correto", "Product_Name_1", products.get(0).getName());
    }

    @Test
    @UsingDataSet("dataset/product_simple.xml")
    public void testUpdate() throws Exception {
        templateJPA.update(this.createEntity(1l, "Product_Name_2"));

        List<Product> products = templateJPA.list(Product.class);
        assertEquals("Não foi alterado o produto", 1, products.size());
        assertEquals("Deveria ser retornado o Id inserido anteriormente", Long.valueOf(1l),
                products.get(0).getId());
        assertEquals("Não foi alterado o nome do produto correto", "Product_Name_2", products.get(0).getName());
    }

    @Ignore
    @Test
    public void testUpdateList() throws Exception {
        // TODO
    }

    @Test
    @UsingDataSet("dataset/product_simple.xml")
    public void testRemove() throws Exception {
        templateJPA.remove(this.createEntity(1l, null));

        List<Product> deletedProducts = templateJPA.list(Product.class);
        assertEquals("Não foi removido o produto", 0, deletedProducts.size());
    }

    @Ignore
    @Test
    public void testRemoveList() throws Exception {
        // TODO
    }

    @Test
    @UsingDataSet("dataset/product_simple.xml")
    public void testFind() throws Exception {
        Product product = templateJPA.find(Product.class, 1l);

        assertEquals("Id retornado incorreto", Long.valueOf(1l), product.getId());
        assertEquals("Nome do produto retornado incorreto", "Product_Test_1", product.getName());
        assertEquals("Valor do produto retornado incorreto", BigDecimal.valueOf(123.23), product.getValue());
    }

    @Ignore
    @Test
    public void testFindByQuery() throws Exception {
        // TODO
    }

    @Ignore
    @Test
    public void testFindByQueryWithParameters() throws Exception {
        // TODO
    }

    @Ignore
    @Test
    public void testList() throws Exception {
        // TODO
    }

    @Ignore
    @Test
    public void testFindByExample() throws Exception {
        // TODO
    }

    private BaseEntity<Long> createEntity(Long id, String name) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        return product;
    }
}