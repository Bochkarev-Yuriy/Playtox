package ru.playtox.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.playtox.dao.abztract.PurchaseDao;
import ru.playtox.models.purchases.Purchase;

@Transactional
@Repository
public class PurchaseDaoImpl extends AbstractDao<Long, Purchase> implements PurchaseDao {

}
