package LLD.standard.car_rental.entities;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class RentalStore {

    private int id;
    private String storeName;
    private String location;
    private long phone;
    public ReentrantLock lock;

    public RentalStore(int id, String storeName, String location, long phone) {
        this.id = id;
        this.storeName = storeName;
        this.location = location;
        this.phone = phone;
        this.lock = new ReentrantLock();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalStore that = (RentalStore) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
