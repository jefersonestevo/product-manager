package br.com.manager.web;

import br.com.manager.infra.dao.utils.DatabaseTemplateJPA;
import br.com.manager.model.entity.Product;
import br.com.manager.services.IProductService;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ManagedBean(name = "productBean")
public class ProductBean {

    @Inject
    private IProductService productService;

    public String getLabel() {
        Product product = new Product();
        product.setName(Double.toString(Math.random()));

        productService.insert(product);

        return "Product Manager App Test";
    }
}
