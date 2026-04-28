package LLD.standard.InventorySystem.factory;

import LLD.standard.InventorySystem.entities.ClothingProduct;
import LLD.standard.InventorySystem.entities.ElectronicsProduct;
import LLD.standard.InventorySystem.entities.GroceryProduct;
import LLD.standard.InventorySystem.entities.Product;
import LLD.standard.InventorySystem.enums.ProductCategory;

public class ProductFactory {

    public Product createProduct(ProductCategory category, String sku, String name, double price, int quantity) {
        switch (category) {
            case ELECTRONICS:
                return new ElectronicsProduct(sku, name, price, quantity);
            case CLOTHING:
                return new ClothingProduct(sku, name, price, quantity);
            case GROCERY:
                return new GroceryProduct(sku, name, price, quantity);
            default:
                throw new IllegalArgumentException("Unsupported product category: " + category);
        }
    }
}
