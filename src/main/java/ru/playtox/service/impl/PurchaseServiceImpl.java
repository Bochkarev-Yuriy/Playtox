package ru.playtox.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.playtox.dao.abstr.PurchaseDao;
import ru.playtox.model.purchases.Purchase;
import ru.playtox.service.abstr.PurchaseService;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseDao purchaseDao;

	@Override
	public void addPurchase(Purchase purchase) {
		purchaseDao.persist(purchase);
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
		purchaseDao.update(purchase);
	}

	@Override
	public void deletePurchaseById(Long id) {
		purchaseDao.deleteByKey(id);
	}
}
