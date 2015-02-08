package br.com.manager.infra.dao.utils;

import br.com.manager.infra.exception.ProductManagerException;
import br.com.manager.model.entity.BaseEntity;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class BaseDAOJPA<ENT extends BaseEntity<ID>, ID> {

    @Inject
    private DatabaseTemplateJPA template;

    protected abstract Class<ENT> getEntityClass();

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ENT find(ID id) throws ProductManagerException {
        return getTemplate().find(getEntityClass(), id);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<ENT> findList() throws ProductManagerException {
        return getTemplate().findList(getEntityClass());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insert(ENT entity) throws ProductManagerException {
        getTemplate().insert(entity);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ENT update(ENT entity) throws ProductManagerException {
        return getTemplate().update(entity);
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return getTemplate().getEntityManager().getCriteriaBuilder();
    }

    protected TypedQuery<ENT> createQuery(CriteriaQuery<ENT> cq) {
        return getTemplate().getEntityManager().createQuery(cq);
    }

    protected DatabaseTemplateJPA getTemplate() {
        return template;
    }

    protected void setTemplate(DatabaseTemplateJPA template) {
        this.template = template;
    }
}
