package br.com.manager.view.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.manager.model.entity.Product;
import br.com.manager.model.filter.ProductFilter;
import br.com.manager.services.IProductService;

@ConversationScoped
@Named("productBean")
public class ProductBean implements Serializable {

    private ProductFilter filter;
    private List<Product> productList = new ArrayList<>();

    private Product product;
    private boolean searched = false;

    @Inject
    private Conversation conversation;

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
        endConversation();
        return "/pages/web/product/search.xhtml";
    }

    public String search() {
        product = new Product();
        productList = productService.listAll(filter);
        searched = Boolean.TRUE;
        return "/pages/web/product/search.xhtml";
    }

    public String goToInsertPage() {
        beginConversation();
        product = new Product();

        return "/pages/web/product/insert.xhtml";
    }

    public String insert() {
        productService.insert(product);
        return search();
    }

    public String goToEditPage(Long id) {
        beginConversation();
        product = productService.findById(id);
        return "/pages/web/product/edit.xhtml";
    }

    public void edit() {
        productService.update(product);
    }

    private void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
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
