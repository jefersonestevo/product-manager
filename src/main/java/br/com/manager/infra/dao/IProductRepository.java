package br.com.manager.infra.dao;

import br.com.manager.model.entity.Product;
import br.com.manager.model.filter.ProductFilter;

import java.util.List;

public interface IProductRepository {

    public void insert(Product product);

    public List<Product> listAll(ProductFilter productFilter);

}
