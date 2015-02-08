package br.com.manager.test;

import br.com.manager.infra.dao.utils.DatabaseTemplateJPA;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;

@Dependent
@Alternative
public class DataBaseTemplateTestJPANoDatabase extends DatabaseTemplateJPA {

    @Override
    public EntityManager getEntityManager() {
        return null;
    }

}
