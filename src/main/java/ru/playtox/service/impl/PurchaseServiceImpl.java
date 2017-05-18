package ru.playtox.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.playtox.dao.abstr.PurchaseDao;
import ru.playtox.dao.impl.exceptions.PersistException;
import ru.playtox.model.purchases.Purchase;
import ru.playtox.service.abstr.PurchaseService;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private final static Logger logger = Logger.getLogger(PurchaseServiceImpl.class);

	@Autowired
	private PurchaseDao purchaseDao;

	@Override
	public void addPurchase(Purchase purchase) {
		try {
			purchaseDao.persist(purchase);
			logger.info("Added : " + purchase);
		} catch (HibernateException e) {
			logger.error("Failed to add an purchase " + purchase);
			throw new PersistException("Failed to add an purchase", e);
		}
	}

	@Override
	public Purchase getPurchaseById(Long id) {
		return purchaseDao.getEntityByKey(id);
	}

	@Override
	public List<Purchase> getAllPurchase() {
		return purchaseDao.getAllEntity();
	}

	@Override
	public void updatePurchase(Purchase purchase) {
		try {
			purchaseDao.update(purchase);
			logger.info("Update : " + purchase);
		} catch (HibernateException e) {
			logger.error("Failed to update an purchase " + purchase);
			throw new PersistException("Failed to update an purchase", e);
		}
	}

	@Override
	public void deletePurchaseById(Long id) {
		try {
			purchaseDao.deleteByKey(id);
			logger.info("Deleted purchase id=" + id);
		} catch (HibernateException e) {
			logger.error("Failed to deleted an purchase id=" + id);
			throw new PersistException("Failed to deleted an purchase", e);
		}
	}
}
