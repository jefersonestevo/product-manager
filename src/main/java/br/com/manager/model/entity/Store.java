package br.com.manager.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "store")
@SequenceGenerator(name = "seq_store", sequenceName = "seq_store", initialValue = 1000)
public class Store implements PMEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_store")
    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany
    @JoinTable(name = "store_product", joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")}
            ,inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> productList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
