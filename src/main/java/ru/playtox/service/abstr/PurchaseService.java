package ru.playtox.service.abstr;

import ru.playtox.model.purchases.Purchase;

import java.util.List;

public interface PurchaseService {

	void addPurchase(Purchase purchase);

	Purchase getPurchaseById(Long id);

	List<Purchase> getAllPurchase();

	void updatePurchase(Purchase purchase);

	void deletePurchaseById(Long id);
}
