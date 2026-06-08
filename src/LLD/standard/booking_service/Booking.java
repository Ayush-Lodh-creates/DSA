package LLD.standard.booking_service;

public class Booking {

    private String bookingId;
    private String userId;
    private String seatId;
    private BookingStatus bookingStatus;

    public Booking(String bookingId, String userId, String seatId, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.seatId = seatId;
        this.bookingStatus = bookingStatus;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
