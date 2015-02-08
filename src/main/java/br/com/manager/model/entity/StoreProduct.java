package br.com.manager.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "store_product")
public class StoreProduct implements BaseEntity<StoreProductKey> {

    @EmbeddedId
    private StoreProductKey id;

    @Column
    @NotNull
    private Integer amount = 0;

    public StoreProductKey getId() {
        return id;
    }

    public void setId(StoreProductKey id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
