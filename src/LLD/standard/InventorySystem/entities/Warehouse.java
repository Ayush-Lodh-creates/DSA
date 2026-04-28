package LLD.standard.InventorySystem.entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private int id;
    private String name;
    private String location;
    private Map<String, Product> products;

    public Warehouse(int id, String name, String location, Map<String, Product> products) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.products = products;
    }

    public Warehouse(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.products = new HashMap<>();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addProduct(Product product) {
        product.setQuantity(product.getQuantity());
        products.put(product.getSku(), product);
        System.out.println("Added " + product.getQuantity() + " of " + product.getName() + " to warehouse " + name);
    }

    public void addProduct(Product product, int quantity) {
        String sku = product.getSku();
        if(products.containsKey(sku)) {
            Product existingProduct = products.get(sku);
            existingProduct.addStock(quantity);
        } else {
            product.setQuantity(quantity);
            products.put(sku, product);
        }
        System.out.println("Added " + quantity + " of " + product.getName() + " to warehouse " + name);
    }

    public boolean removeProduct(String sku, int quantity) {
        if(products.containsKey(sku)) {
            Product product = products.get(sku);
            int currentQuantity = product.getQuantity();
            if(currentQuantity >= quantity) {
                product.removeStock(quantity);
                System.out.println("Removed " + quantity + " of " + product.getName());
                if(product.getQuantity() == 0) {
                    products.remove(sku);
                    System.out.println("Product" + product.getName() + "removed from inventory as quantity is now zero");
                }
                return true;
            } else {
                System.out.println("Error: Insufficient inevntory, requested " + quantity);
                return false;
            }
        } else {
            System.out.println("Error: Product with sku " + sku + " not found.");
            return false;
        }
    }

    public int getAvailableQuantity(String sku) {
        if(products.containsKey(sku)) {
            return products.get(sku).getQuantity();
        } else {
            System.out.println("Error: Product with sku " + sku + " not found.");
            return 0;
        }
    }

    public Product getProductBySku(String sku) {
        return products.get(sku);
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }
}
