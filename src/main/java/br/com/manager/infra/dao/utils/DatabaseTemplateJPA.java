package br.com.manager.infra.dao.utils;

import br.com.manager.infra.exception.ProductManagerException;
import br.com.manager.infra.extensions.Logged;
import br.com.manager.model.entity.BaseEntity;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Logged
@Named("template_jpa")
public class DatabaseTemplateJPA {

    @PersistenceContext
    private EntityManager entityManager;

    public <E extends BaseEntity<ID>, ID> void insert(E entity) throws ProductManagerException {
        try {
            getEntityManager().persist(entity);
            getEntityManager().flush();
        } catch (RuntimeException e) {
            throw JPAExceptionTranslator.getTranslatedException(e);
        }
    }

    public <E extends BaseEntity<ID>, ID> E update(E entity) throws ProductManagerException {
        try {
            entity = getEntityManager().merge(entity);
            getEntityManager().flush();
            return entity;
        } catch (RuntimeException e) {
            throw JPAExceptionTranslator.getTranslatedException(e);
        }
    }

    public <E extends BaseEntity<ID>, ID> List<E> updateList(List<E> entityList)
            throws ProductManagerException {
        try {
            List<E> updatedEntities = new ArrayList<E>();
            for (E entity : entityList) {
                if (entity.getId() == null) {
                    getEntityManager().persist(entity);
                } else {
                    entity = getEntityManager().merge(entity);
                }
                updatedEntities.add(entity);
            }
            getEntityManager().flush();
            return updatedEntities;
        } catch (RuntimeException e) {
            throw JPAExceptionTranslator.getTranslatedException(e);
        }
    }

    public <E extends BaseEntity<ID>, ID> void remove(E entity) throws ProductManagerException {
        try {
            E entityRemocao = getEntityManager().merge(entity);
            getEntityManager().remove(entityRemocao);
            getEntityManager().flush();
        } catch (RuntimeException e) {
            throw JPAExceptionTranslator.getTranslatedException(e);
        }
    }

    public <E extends BaseEntity<ID>, ID> void removeList(List<E> entityList)
            throws ProductManagerException {
        try {
            for (E entity : entityList) {
                E entityRemocao = getEntityManager().merge(entity);
                getEntityManager().remove(entityRemocao);
            }
            getEntityManager().flush();
        } catch (RuntimeException e) {
            throw JPAExceptionTranslator.getTranslatedException(e);
        }
    }

    public <E extends BaseEntity<ID>, ID> E find(Class<E> entity, ID id) throws ProductManagerException {
        try {
            return getEntityManager().find(entity, id);
        } catch (RuntimeException e) {
            throw JPAExceptionTranslator.getTranslatedException(e);
        }
    }

    public <E extends BaseEntity<ID>, ID> List<E> findByQuery(Class<E> entityClass, String query)
            throws ProductManagerException {
        try {
            return getEntityManager().createQuery(query, entityClass)
                    .getResultList();
        } catch (RuntimeException e) {
            throw JPAExceptionTranslator.getTranslatedException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <E extends BaseEntity<ID>, ID> List<E> findByQueryWithParameters(Class<E> entityClass, String queryStr,
                                                                            Object[] params) throws ProductManagerException {
        try {
            Query query = getEntityManager().createQuery(queryStr,
                    entityClass);
            if (params != null)
                for (int i = 0; i < params.length; i++) {
                    query.setParameter((i + 1), params[i]);
                }
            return query.getResultList();
        } catch (RuntimeException e) {
            throw JPAExceptionTranslator.getTranslatedException(e);
        }
    }

    public <E extends BaseEntity<ID>, ID> List<E> list(Class<E> entity)
            throws ProductManagerException {
        try {
            CriteriaQuery<E> crit = this.createCriteria(entity);
            TypedQuery<E> q = entityManager.createQuery(crit);
            return q.getResultList();

        } catch (RuntimeException e) {
            throw JPAExceptionTranslator.getTranslatedException(e);
        }
    }

    public <E extends BaseEntity<ID>, ID> List<E> listByIds(Class<E> entity, List<ID> listaIds)
            throws ProductManagerException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<E> crit = cb.createQuery(entity);
            Root<E> root = crit.from(entity);
            crit.select(root).where(root.get("id").in(listaIds));
            TypedQuery<E> q = entityManager.createQuery(crit);
            return q.getResultList();
        } catch (RuntimeException e) {
            throw JPAExceptionTranslator.getTranslatedException(e);
        }
    }

    private <E extends BaseEntity<ID>, ID> CriteriaQuery<E> createCriteria(Class<E> entityClass) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(entityClass);
        Root<E> root = cq.from(entityClass);
        cq.select(root);
        return cq;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}