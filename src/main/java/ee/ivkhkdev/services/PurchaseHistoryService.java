package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Repository;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.PurchaseHistory;

import java.util.List;

public class PurchaseHistoryService implements Service<PurchaseHistory> {
    private final AppHelper<PurchaseHistory> purchaseHistoryAppHelper;
    private final Repository<PurchaseHistory> repository;

    public PurchaseHistoryService(AppHelper<PurchaseHistory> purchaseHistoryAppHelper, Repository<PurchaseHistory> repository) {
        this.purchaseHistoryAppHelper = purchaseHistoryAppHelper;
        this.repository = repository;
    }

    @Override
    public boolean add() {
        PurchaseHistory libraryCard = purchaseHistoryAppHelper.create();
        if (libraryCard == null) return false;
        try {
            repository.save(libraryCard);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }

    @Override
    public boolean print() {
        return purchaseHistoryAppHelper.printList(repository.load());
    }

    @Override
    public List<PurchaseHistory> list() {
        return repository.load();
    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public boolean printList() {
        return false;
    }
}

