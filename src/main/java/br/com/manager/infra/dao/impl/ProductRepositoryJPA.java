package br.com.manager.infra.dao.impl;

import br.com.manager.infra.dao.IProductRepository;
import br.com.manager.infra.dao.utils.BaseDAOJPA;
import br.com.manager.model.entity.Product;
import br.com.manager.model.filter.ProductFilter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class ProductRepositoryJPA extends BaseDAOJPA<Product, Long> implements IProductRepository {

    private static Logger LOG = LoggerFactory.getLogger(ProductRepositoryJPA.class);

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    public List<Product> listAll(ProductFilter productFilter) {
        LOG.debug("ProductRepositoryJPA.listAll: {}", productFilter);

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
