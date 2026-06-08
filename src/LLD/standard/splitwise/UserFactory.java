package LLD.standard.splitwise;

import java.util.UUID;

public class UserFactory {

    public static User createUser(String userType, String name) {

        switch (userType) {
            case "normal":
                return new User(UUID.randomUUID().toString(), name, UserType.NORMAL);

            case "premium":
                return new User(UUID.randomUUID().toString(), name, UserType.PREMIUM);

            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }
}
