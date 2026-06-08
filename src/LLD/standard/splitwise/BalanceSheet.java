package LLD.standard.splitwise;

import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {

    Map<User, Map<User, Double>> balanceSheet;

    public BalanceSheet() {
        balanceSheet = new HashMap<>();
    }

    public void updateExpense(double amount, User lender, User borrower) {

        balanceSheet.putIfAbsent(lender, new HashMap<>());

        Map<User, Double> lenderBalance = balanceSheet.get(lender);
        lenderBalance.put(borrower, lenderBalance.getOrDefault(borrower, 0.0) + amount);
        balanceSheet.put(lender, lenderBalance);

        balanceSheet.putIfAbsent(borrower, new HashMap<>());

        Map<User, Double> borrowerBalance = balanceSheet.get(borrower);
        borrowerBalance.put(lender, borrowerBalance.getOrDefault(lender, 0.0) - amount);
        balanceSheet.put(borrower, borrowerBalance);
    }

    public void showBalance(User user) {

        Map<User, Double> userBalance = balanceSheet.getOrDefault(user, new HashMap<>());

        for(Map.Entry<User, Double> entry : userBalance.entrySet()) {
            User balanceWithUser = entry.getKey();
            double amount = entry.getValue();
            if(amount < 0) {
                System.out.println("User : " + user.getName() + " owes " + Math.abs(amount) + " to " + balanceWithUser.getName());
            } else if(amount > 0) {
                System.out.println("User : " + user.getName() + " lends " + amount + " to " + balanceWithUser.getName());
            }
        }
    }
}
