package LLD.standard.splitwise;

import java.util.List;

public class Expense {

    private String id;
    private User lender;
    private double totalAmount;
    private List<Split> splits;

    public Expense(String id, User lender, double totalAmount, List<Split> splits) {
        this.id = id;
        this.lender = lender;
        this.totalAmount = totalAmount;
        this.splits = splits;
    }

    public User getLender() {
        return lender;
    }

    public void setLender(User lender) {
        this.lender = lender;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }
}
