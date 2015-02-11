package br.com.manager.model.entity;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public abstract class BaseEntity<ID> implements Serializable {

    public abstract ID getId();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
