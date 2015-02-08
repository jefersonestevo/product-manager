package br.com.manager.web;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "productBean")
public class ProductManagerBean {

    public String getLabel() {
        return "Product Manager App Test";
    }
}
