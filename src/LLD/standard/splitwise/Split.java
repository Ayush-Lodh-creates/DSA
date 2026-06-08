package LLD.standard.splitwise;

public class Split {

    private User user;
    private Double splitAmount;

    public Split(User user) {
        this.user = user;
    }

    public void setSplitAmount(Double splitAmount) {
        this.splitAmount = splitAmount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Double getSplitAmount() {
        return splitAmount;
    }
}
