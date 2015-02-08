package br.com.manager.view.mobile;

import br.com.manager.model.entity.Product;
import br.com.manager.services.IProductService;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;

@ManagedBean(name = "mobileProductBean")
public class MobileProductBean {

    private List<Product> productList;

    @Inject
    private IProductService productService;

    public List<Product> getProductList() {
        this.productList = productService.listAll(null);
        return productList;
    }
}
