package LLD.standard.movie_booking.entities;

public class Screen {

    private String screenId;
    private int screenNumber;
    private Movie movie;

    public Screen(String screenId, int screenNumber, Movie movie) {
        this.screenId = screenId;
        this.screenNumber = screenNumber;
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
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
