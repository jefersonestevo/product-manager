package br.com.manager.services;

import br.com.manager.model.entity.Product;
import br.com.manager.model.filter.ProductFilter;

import java.util.List;

public interface IProductService {

    public List<Product> listAll(ProductFilter productFilter);

    public void insert(Product product);

    public void update(Product product);

}
