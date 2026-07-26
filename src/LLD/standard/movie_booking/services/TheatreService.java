package LLD.standard.movie_booking.services;

import LLD.standard.movie_booking.entities.Screen;
import LLD.standard.movie_booking.entities.Theatre;
import LLD.standard.movie_booking.entities.User;
import LLD.standard.movie_booking.enums.UserType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreService {

    Map<Theatre, List<Screen>> theatreScreensMap = new HashMap<>();

    public void addTheatre(Theatre theatre, List<Screen> screens, User user) {
        if(!user.getUserType().equals(UserType.ADMIN)) {
            throw new IllegalArgumentException("Only admin users can add theatres.");
        }
        theatreScreensMap.put(theatre, screens);
    }

    public void addScreen(Theatre theatre, Screen screen, User user) {
        if(!user.getUserType().equals(UserType.ADMIN)) {
            throw new IllegalArgumentException("Only admin users can add screens.");
        }
        theatreScreensMap.get(theatre).add(screen);
    }

    public List<Screen> getScreensForTheatre(Theatre theatre) {
        return theatreScreensMap.getOrDefault(theatre, new ArrayList<>());
    }
}