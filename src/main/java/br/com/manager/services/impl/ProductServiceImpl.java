package br.com.manager.services.impl;

import br.com.manager.infra.dao.IProductRepository;
import br.com.manager.model.entity.Product;
import br.com.manager.services.IProductService;
import br.com.manager.services.filter.ProductFilter;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Stateless
public class ProductServiceImpl implements IProductService {

    @Inject
    private IProductRepository productRepository;

    @Override
    public List<Product> listAll(ProductFilter productFilter) {
        // TODO
        return null;
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
