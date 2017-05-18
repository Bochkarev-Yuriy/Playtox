package ru.playtox.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.playtox.dao.abstr.ProductDao;
import ru.playtox.model.products.Product;


@Transactional
@Repository
public class ProductDaoImpl extends AbstractDao<Long, Product> implements ProductDao {

}
