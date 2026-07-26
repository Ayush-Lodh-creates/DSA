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

    public Map<Theatre,Screen> showAllTheatres(String movieName) {
        Map<Theatre, Screen> theatreScreenMap = new HashMap<>();
        for(Map.Entry<Theatre, List<Screen>> entry : theatreScreensMap.entrySet()) {
            for(Screen screen : entry.getValue()) {
                if(screen.getMovie().getName().equalsIgnoreCase(movieName)) {
                    theatreScreenMap.put(entry.getKey(), screen);
                    break; // Break to avoid adding the same theatre multiple times
                }
            }
        }
        return theatreScreenMap;
    }
}