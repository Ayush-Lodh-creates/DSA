package LLD.standard.movie_booking.entities;

public class Theatre {

    private String theatreId;
    private String theatreName;

    public Theatre(String theatreId, String theatreName) {
        this.theatreId = theatreId;
        this.theatreName = theatreName;
    }

    public String getTheatreId() {
        return theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreId(String theatreId) {
        this.theatreId = theatreId;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }
}
