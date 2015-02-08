package br.com.manager.test;

import br.com.manager.infra.dao.utils.DatabaseTemplateJPA;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Dependent
@Alternative
public class DataBaseTemplateTestJPA extends DatabaseTemplateJPA {

    @PersistenceContext(name = "product-manager_test")
    protected EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
