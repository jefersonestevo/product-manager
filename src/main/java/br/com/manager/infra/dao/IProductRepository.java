package br.com.manager.infra.dao;

import br.com.manager.model.entity.Product;
import br.com.manager.model.filter.ProductFilter;

import java.io.Serializable;
import java.util.List;

public interface IProductRepository extends Serializable {

    public void insert(Product product);

    public Product update(Product product);

    public List<Product> listAll(ProductFilter productFilter);

    public Product find(Long id);

}
