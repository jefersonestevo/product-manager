package br.com.manager.infra.dao.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
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
public class DatabaseTemplateJPAIntegrationTest {

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
    @ShouldMatchDataSet(value = "dataset/product_simple.xml", excludeColumns = {"id"})
    public void testInsert() throws Exception {
        templateJPA.insert(this.createEntity(null, "Product_Test_1", BigDecimal.valueOf(123.23)));
    }

    @Test
    @UsingDataSet("dataset/product_simple.xml")
    @ShouldMatchDataSet("dataset/product_updated.xml")
    public void testUpdate() throws Exception {
        templateJPA.update(this.createEntity(1l, "Product_Name_2", BigDecimal.valueOf(159.22)));
    }

    @Test
    @UsingDataSet("dataset/product_list.xml")
    @ShouldMatchDataSet("dataset/product_list_updated.xml")
    public void testUpdateList() throws Exception {
        List<BaseEntity<Long>> listToUpdate = new ArrayList<>();
        listToUpdate.add(this.createEntity(1l, "Product_Updated_1", BigDecimal.valueOf(240.12)));
        listToUpdate.add(this.createEntity(2l, "Product_Updated_2", BigDecimal.valueOf(241.12)));
        listToUpdate.add(this.createEntity(3l, "Product_Updated_3", BigDecimal.valueOf(242.12)));
        templateJPA.updateList(listToUpdate);
    }

    @Test
    @UsingDataSet("dataset/product_simple.xml")
    public void testRemove() throws Exception {
        templateJPA.remove(this.createEntity(1l, null, null));

        List<Product> deletedProducts = templateJPA.list(Product.class);
        assertEquals("The products were not removed", 0, deletedProducts.size());
    }

    @Test
    @UsingDataSet("dataset/product_list.xml")
    public void testRemoveList() throws Exception {
        List<BaseEntity<Long>> listToBeRemoved = new ArrayList<>();
        listToBeRemoved.add(this.createEntity(1l, null, null));
        listToBeRemoved.add(this.createEntity(2l, null, null));
        listToBeRemoved.add(this.createEntity(3l, null, null));

        templateJPA.removeList(listToBeRemoved);

        List<Product> deletedProducts = templateJPA.list(Product.class);
        assertEquals("Não foi removido o produto", 0, deletedProducts.size());
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

    @Test
    @UsingDataSet("dataset/product_list.xml")
    public void testList() throws Exception {
        List<Product> list = templateJPA.list(Product.class);
        assertNotNull("List should not be null", list);
        assertEquals(3, list.size());
    }

    @Test
    @UsingDataSet("dataset/product_list.xml")
    public void testListByIds() throws Exception {
        List<Product> products = templateJPA.listByIds(Product.class, Arrays.asList(1l, 2l));
        assertNotNull("List should not be null", products);
        assertEquals(2, products.size());
    }

    private BaseEntity<Long> createEntity(Long id, String name, BigDecimal value) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setValue(value);
        return product;
    }
}