package br.com.manager.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class StoreProductKey implements Serializable {

    @OneToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreProductKey that = (StoreProductKey) o;

        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (store != null ? !store.equals(that.store) : that.store != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = store != null ? store.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }
}
