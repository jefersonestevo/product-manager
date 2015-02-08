package br.com.manager.services;

import br.com.manager.model.entity.Store;
import br.com.manager.services.filter.StoreFilter;

import java.util.List;

public interface IStoreService {

    public List<Store> listAll(StoreFilter storeFilter);

    public void insert(Store store);

    public void update(Store store);

}
