package br.com.manager.infra.dao.impl;

import br.com.manager.infra.dao.IStoreRepository;
import br.com.manager.infra.dao.utils.BaseDAOJPA;
import br.com.manager.model.entity.Store;

import javax.inject.Named;

@Named
public class StoreRepositoryJPA extends BaseDAOJPA<Store, Long> implements IStoreRepository {
    @Override
    protected Class<Store> getEntityClass() {
        return Store.class;
    }
}
