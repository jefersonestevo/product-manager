package br.com.manager.services.impl;

import br.com.manager.infra.dao.IProductRepository;
import br.com.manager.model.filter.ProductFilter;
import br.com.manager.test.AbstractUnitTest;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductServiceImplTest extends AbstractUnitTest {

    private ProductServiceImpl productService;

    private IProductRepository productRepository;

    @Override
    public void init() throws Exception {
        productService = new ProductServiceImpl();

        productRepository = mock(IProductRepository.class);

        setField(productService, "productRepository", productRepository);
    }

    @Test
    public void testListAll() throws Exception {
        ProductFilter filter = new ProductFilter();
        productService.listAll(filter);

        verify(productRepository).listAll(eq(filter));
    }

    @Test
    public void testListAll_NullAsArgument() throws Exception {
        productService.listAll(null);

        verify(productRepository).listAll(isNull(ProductFilter.class));
    }

    @Test
    public void testInsert() throws Exception {
        // TODO
    }

    @Test
    public void testUpdate() throws Exception {
        // TODO
    }
}