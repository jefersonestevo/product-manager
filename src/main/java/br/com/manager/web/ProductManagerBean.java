package br.com.manager.web;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ManagedBean(name = "productBean")
public class ProductManagerBean {

    @PersistenceContext(name = "product-manager")
    private EntityManager entityManager;

    public String getLabel() {
        return "Product Manager App Test";
    }
}
