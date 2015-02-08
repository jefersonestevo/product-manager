package br.com.manager.services.impl;

import br.com.manager.infra.dao.IProductRepository;
import br.com.manager.model.entity.Product;
import br.com.manager.model.filter.ProductFilter;
import br.com.manager.services.IProductService;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProductServiceImpl implements IProductService {

    @Inject
    private IProductRepository productRepository;

    @Override
    public List<Product> listAll(ProductFilter productFilter) {
        return productRepository.listAll(productFilter);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insert(Product product) {
        productRepository.insert(product);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Product product) {
        // TODO
    }
}
