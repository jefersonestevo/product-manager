package br.com.manager.infra.dao.impl;

import br.com.manager.infra.dao.IProductRepository;
import br.com.manager.infra.dao.utils.BaseDAOJPA;
import br.com.manager.infra.dao.utils.DatabaseTemplateJPA;
import br.com.manager.infra.extensions.Logged;
import br.com.manager.model.entity.Product;
import br.com.manager.model.filter.ProductFilter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Logged
@Named
public class ProductRepositoryJPA extends BaseDAOJPA<Product, Long> implements IProductRepository {

    @Inject
    private DatabaseTemplateJPA template;

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    protected DatabaseTemplateJPA getTemplate() {
        return template;
    }

    @Override
    public List<Product> listAll(ProductFilter productFilter) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder query = new StringBuilder();

        query.append(" SELECT p FROM product p ");
        query.append(" WHERE 1=1 ");

        if (productFilter != null) {
            if (StringUtils.isNotBlank(productFilter.getName())) {
                query.append(" AND p.name like ? ");
                params.add("%" + productFilter.getName() + "%");
            }
        }

        query.append(" ORDER BY p.name ");

        return getTemplate().findByQueryWithParameters(getEntityClass(), query.toString(), params.toArray());
    }
}
