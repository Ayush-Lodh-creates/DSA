package LLD.standard.InventorySystem.singleton;

import LLD.standard.InventorySystem.entities.Product;
import LLD.standard.InventorySystem.entities.Warehouse;
import LLD.standard.InventorySystem.factory.ProductFactory;
import LLD.standard.InventorySystem.observer.InventoryObserver;
import LLD.standard.InventorySystem.strategy.ReplenishmentStrategy;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private static InventoryManager instance;

    private List<Warehouse> warehouses;
    private ProductFactory productFactory;
    private ReplenishmentStrategy replenishmentStrategy;
    private List<InventoryObserver> observers;

    private InventoryManager() {
        warehouses = new ArrayList<>();
        productFactory = new ProductFactory();
        observers = new ArrayList<>();
    }

    public static synchronized InventoryManager getInstance() {
        if(instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void setReplenishmentStrategy(ReplenishmentStrategy strategy) {
        this.replenishmentStrategy = strategy;
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }

    public void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
    }

    public Product getProductBySku(String sku) {
        for(Warehouse warehouse : warehouses) {
            Product product = warehouse.getProductBySku(sku);
            if(product != null) {
                return product;
            }
        }
        return null;
    }

    public void performInventoryCheck() {
        for(Warehouse warehouse : warehouses) {
            for(Product product : warehouse.getAllProducts()) {
                if(product.getQuantity() < product.getThreshold()) {
                    if(replenishmentStrategy != null) {
                        replenishmentStrategy.replenish(product);
                    }
                }
            };
        }
    }

    public void checkAndReplenish(String sku) {
        Product product = getProductBySku(sku);
        if(product != null) {
            if(product.getQuantity() < product.getThreshold()) {
                if(replenishmentStrategy != null) {
                    replenishmentStrategy.replenish(product);
                }
            }
        }
    }

    public void setObservers(List<InventoryObserver> observers) {
        this.observers = observers;
    }

    public void addObserver(InventoryObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(InventoryObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers(Product product) {
        for(InventoryObserver observer : observers) {
            observer.update(product);
        }
    }
}
