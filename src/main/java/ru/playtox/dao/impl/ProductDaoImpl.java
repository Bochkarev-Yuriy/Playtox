package ru.playtox.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.playtox.dao.abztract.ProductDao;
import ru.playtox.models.products.Product;

import java.util.List;

@Transactional
@Repository
public class ProductDaoImpl extends AbstractDao<Long, Product> implements ProductDao {

}
