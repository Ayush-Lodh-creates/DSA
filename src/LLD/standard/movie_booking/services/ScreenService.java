package LLD.standard.movie_booking.services;

import LLD.standard.movie_booking.entities.Screen;
import LLD.standard.movie_booking.entities.Seat;
import LLD.standard.movie_booking.entities.User;
import LLD.standard.movie_booking.enums.UserType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenService {

    private Map<Screen, List<Seat>> screenSeatMap = new HashMap<>();

    public void addScreen(Screen screen, List<Seat> seats, User user) {
        if(UserType.EXTERNAL.equals(user.getUserType())) {
            throw new IllegalArgumentException("Only admin users can add screens.");
        }
        screenSeatMap.put(screen, seats);
    }

    public void addSeat(Screen screen, Seat seat, User user) {
        if(UserType.EXTERNAL.equals(user.getUserType())) {
            throw new IllegalArgumentException("Only admin users can add screens.");
        }
        screenSeatMap.get(screen).add(seat);
    }

    public List<Seat> getSeatsForScreen(Screen screen) {
        return screenSeatMap.getOrDefault(screen, new ArrayList<>());
    }
}
