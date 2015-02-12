package br.com.manager.view.web;

import br.com.manager.model.entity.Product;
import br.com.manager.model.filter.ProductFilter;
import br.com.manager.services.IProductService;
import br.com.manager.view.BaseBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named("productBean")
public class ProductBean extends BaseBean {

    private ProductFilter filter;
    private List<Product> productList = new ArrayList<>();

    private Product product;
    private boolean searched = false;

    @Inject
    private IProductService productService;

    @PostConstruct
    public void init() {
        filter = new ProductFilter();
        product = new Product();
        searched = false;
    }

    public String goToSearchPage() {
        init();
        return "/pages/web/product/search.xhtml?faces-redirect=true";
    }

    public String search() {
        product = new Product();
        productList = productService.listAll(filter);
        searched = Boolean.TRUE;
        return "/pages/web/product/search.xhtml";
    }

    public String goToInsertPage() {
        product = new Product();

        return "/pages/web/product/insert.xhtml";
    }

    public String insert() {
        productService.insert(product);
        addInfoMessage(getMessage("Product.Insert.Success"));
        return search();
    }

    public String goToEditPage(Long id) {
        product = productService.findById(id);
        return "/pages/web/product/edit.xhtml";
    }

    public void edit() {
        productService.update(product);
        addInfoMessage(getMessage("Product.Edit.Success"));
    }

    public ProductFilter getFilter() {
        return filter;
    }

    public void setFilter(ProductFilter filter) {
        this.filter = filter;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isSearched() {
        return searched;
    }

    public void setSearched(boolean searched) {
        this.searched = searched;
    }
}
