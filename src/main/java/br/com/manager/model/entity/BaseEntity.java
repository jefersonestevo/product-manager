package br.com.manager.model.entity;

import java.io.Serializable;

public interface BaseEntity<ID> extends Serializable {

    public ID getId();

}
