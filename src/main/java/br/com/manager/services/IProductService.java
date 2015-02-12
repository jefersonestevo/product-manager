package br.com.manager.services;

import br.com.manager.model.entity.Product;
import br.com.manager.model.filter.ProductFilter;

import java.io.Serializable;
import java.util.List;

public interface IProductService extends Serializable {

    public List<Product> listAll(ProductFilter productFilter);

    public void insert(Product product);

    public void update(Product product);

    public Product findById(Long id);

}
