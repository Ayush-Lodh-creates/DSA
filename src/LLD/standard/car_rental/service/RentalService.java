package LLD.standard.car_rental.service;

import LLD.standard.car_rental.entities.RentalStore;
import LLD.standard.car_rental.entities.Slot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalService {

    private Map<RentalStore, List<Slot>> storeListMap;
    private static RentalService rentalService = null;

    private RentalService() {
        storeListMap = new HashMap<>();
    }

    public static synchronized RentalService getInstance() {
        if(rentalService == null) {
            rentalService = new RentalService();
            return rentalService;
        }
        return rentalService;
    }

    public synchronized void addRentalStore(RentalStore rentalStore, List<Slot> slots) {
        storeListMap.put(rentalStore, slots);
    }

    public synchronized void addNewVehicle(RentalStore rentalStore, Slot slot) {
        storeListMap.computeIfAbsent(rentalStore, k -> new ArrayList<>()).add(slot);
    }

    public synchronized void removeRentalStore(RentalStore rentalStore) {
        storeListMap.remove(rentalStore);
    }

    public synchronized void removeSlotFromRentalStore(RentalStore rentalStore, Slot slot) {
        List<Slot> slots = storeListMap.getOrDefault(rentalStore, new ArrayList<>());
        slots.remove(slot);
    }

    public synchronized int getAvailableSlotsCount(RentalStore rentalStore) {
        List<Slot> slots = storeListMap.get(rentalStore);
        if (slots == null) {
            return 0;
        }
        return (int) slots.stream().filter(Slot::isAvailable).count();
    }

    public synchronized boolean doesSlotBelongToRetail(RentalStore rentalStore, Slot slot) {
        List<Slot> slots = storeListMap.get(rentalStore);
        if (slots == null) {
            return false;
        }
        return slots.contains(slot);
    }
}
