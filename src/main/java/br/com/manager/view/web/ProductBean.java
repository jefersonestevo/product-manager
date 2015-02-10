package br.com.manager.view.web;

import br.com.manager.model.entity.Product;
import br.com.manager.services.IProductService;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Named("productBean")
public class ProductBean {

    @Inject
    private IProductService productService;

    public String getLabel() {
        Product product = new Product();
        product.setName("Product " + Double.toString((int) (Math.round(Math.random() * 100))));
        product.setValue(BigDecimal.valueOf(Math.random() * 1000).setScale(2, RoundingMode.DOWN));

        productService.insert(product);

        return "Product Manager App Test";
    }
}
