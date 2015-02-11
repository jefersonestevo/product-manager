package br.com.manager.infra.dao.impl;

import br.com.manager.infra.dao.IStoreRepository;
import br.com.manager.infra.dao.utils.BaseDAOJPA;
import br.com.manager.infra.dao.utils.DatabaseTemplateJPA;
import br.com.manager.infra.extensions.Logged;
import br.com.manager.model.entity.Store;

import javax.inject.Inject;
import javax.inject.Named;

@Logged
@Named
public class StoreRepositoryJPA extends BaseDAOJPA<Store, Long> implements IStoreRepository {

    @Inject
    private DatabaseTemplateJPA template;

    @Override
    protected Class<Store> getEntityClass() {
        return Store.class;
    }

    @Override
    protected DatabaseTemplateJPA getTemplate() {
        return template;
    }
}
