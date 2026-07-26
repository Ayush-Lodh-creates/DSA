package LLD.standard.movie_booking.entities;

import LLD.standard.movie_booking.enums.UserType;

public class User {

    private String id;
    private String name;
    private String email;
    private UserType userType;

    public User(String id, String name, String email, UserType userType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }
}
