package LLD.standard.movie_booking.observer;

public class EmailSubscriber implements Subscriber {

    private String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void sendNotification(String text) {
        System.out.println("Sending email to " + email + ": " + text);
    }
}
