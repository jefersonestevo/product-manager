package br.com.manager.view.mobile;

import br.com.manager.model.entity.Product;
import br.com.manager.services.IProductService;
import br.com.manager.view.BaseBean;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("mobileProductBean")
public class MobileProductBean extends BaseBean {

    private List<Product> productList;

    @Inject
    private IProductService productService;

    public List<Product> getProductList() {
        this.productList = productService.listAll(null);
        return productList;
    }
}
