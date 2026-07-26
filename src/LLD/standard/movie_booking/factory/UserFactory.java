package LLD.standard.movie_booking.factory;

import LLD.standard.movie_booking.entities.User;
import LLD.standard.movie_booking.enums.UserType;

import java.util.concurrent.atomic.AtomicInteger;

public class UserFactory {

    private static AtomicInteger userIdCounter = new AtomicInteger(1);

    public static User createUser(String userType, String name, String email) {
        return switch (userType.toLowerCase()) {
            case "external" ->
                    new User(String.valueOf(userIdCounter.getAndIncrement()), name, email, UserType.EXTERNAL);
            case "admin" -> new User(String.valueOf(userIdCounter.getAndIncrement()), name, email, UserType.ADMIN);
            default -> throw new IllegalArgumentException("Invalid user type: " + userType);
        };
    }
}
