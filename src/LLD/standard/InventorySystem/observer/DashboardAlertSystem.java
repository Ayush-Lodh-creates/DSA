package LLD.standard.InventorySystem.observer;

import LLD.standard.InventorySystem.entities.Product;

import java.util.List;

public class DashboardAlertSystem implements InventoryObserver{

    private List<String> adminUsers;

    public DashboardAlertSystem(List<String> adminUsers) {
        this.adminUsers = adminUsers;
    }

    @Override
    public void update(Product product) {
        double stockPercentage = ((double) product.getQuantity() / product.getThreshold()) * 100;
        if(stockPercentage <= 25) {
            System.out.println("CRITICAL: " + product.getName() + " stock is critically low (" + stockPercentage + "%). Notifying admins: " + String.join(", ", adminUsers));
            notifyAdmins(product, "CRITICAL");
        } else if(stockPercentage <= 50) {
            System.out.println("HIGH: " + product.getName() + " stock is low (" + stockPercentage + "%). Notifying admins: " + String.join(", ", adminUsers));
            notifyAdmins(product, "HIGH");
        }
    }

    private void notifyAdmins(Product product, String alertLevel) {
        for(String admin : adminUsers) {
            System.out.println("Sending notification to user " + admin + " because of alert of level " + alertLevel + " for product " + product.getName());
        }
    }
}
