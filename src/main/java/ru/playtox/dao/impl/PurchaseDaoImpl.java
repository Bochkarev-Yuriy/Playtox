package ru.playtox.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.playtox.dao.abstr.PurchaseDao;
import ru.playtox.model.Purchase;

@Transactional
@Repository
public class PurchaseDaoImpl extends AbstractDao<Long, Purchase> implements PurchaseDao {

}
