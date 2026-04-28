package LLD.standard.InventorySystem.observer;

import LLD.standard.InventorySystem.entities.Product;

public interface InventoryObserver {
    void update(Product product);
}
