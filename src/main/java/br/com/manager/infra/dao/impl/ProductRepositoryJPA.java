package br.com.manager.infra.dao.impl;

import br.com.manager.infra.dao.IProductRepository;
import br.com.manager.infra.dao.utils.BaseDAOJPA;
import br.com.manager.model.entity.Product;

import javax.inject.Named;

@Named
public class ProductRepositoryJPA extends BaseDAOJPA<Product, Long> implements IProductRepository {
    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }
}
