package br.com.manager.services;

import br.com.manager.model.entity.Store;
import br.com.manager.model.filter.StoreFilter;

import java.io.Serializable;
import java.util.List;

public interface IStoreService extends Serializable {

    public List<Store> listAll(StoreFilter storeFilter);

    public void insert(Store store);

    public void update(Store store);

}
