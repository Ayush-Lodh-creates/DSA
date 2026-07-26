package LLD.standard.movie_booking.entities;

public class Screen {

    private String screenId;
    private int screenNumber;

    public Screen(String screenId, int screenNumber) {
        this.screenId = screenId;
        this.screenNumber = screenNumber;
    }

    public String getScreenId() {
        return screenId;
    }

    public int getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(int screenNumber) {
        this.screenNumber = screenNumber;
    }
}
