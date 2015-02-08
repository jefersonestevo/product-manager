package br.com.manager.services.impl;

import br.com.manager.infra.dao.IStoreRepository;
import br.com.manager.model.entity.Store;
import br.com.manager.model.filter.StoreFilter;
import br.com.manager.services.IStoreService;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class StoreServiceImpl implements IStoreService {

    @Inject
    private IStoreRepository storeRepository;

    @Override
    public List<Store> listAll(StoreFilter storeFilter) {
        // TODO
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insert(Store store) {
        // TODO
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Store store) {
        // TODO
    }
}
