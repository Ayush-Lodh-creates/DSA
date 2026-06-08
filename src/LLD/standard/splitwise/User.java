package LLD.standard.splitwise;

public class User {

    private String id;
    private String name;
    private UserType userType;

    public User(String id, String name, UserType userType) {
        this.id = id;
        this.name = name;
        this.userType = userType;
    }

    public String getUserType() {
        return userType.name();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
