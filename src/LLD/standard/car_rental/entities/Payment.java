package LLD.standard.car_rental.entities;

public class Payment {

    private String paymentId;
    private int price;
    private String userId;

    public Payment(String paymentId, int price, String userId) {
        this.paymentId = paymentId;
        this.price = price;
        this.userId = userId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
