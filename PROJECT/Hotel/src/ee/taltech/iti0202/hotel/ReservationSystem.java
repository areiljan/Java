package ee.taltech.iti0202.hotel;

import java.util.ArrayList;
import java.util.List;

public class ReservationSystem {
    private ArrayList<Hotel> hotelList;

    public ReservationSystem() {
        hotelList = new ArrayList<>();
    }

    public void addHotel(Hotel hotel) {
        hotelList.add(hotel);
    }

    /**
     * Filter by location.
     * @param country - country of the hotel.
     * @param city - hotel in this city.
     * @return - list of hotels that qualify.
     */
    public List<Hotel> filterByLocation(String country, String city) {
        List<Hotel> filteredHotels = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            if (hotel.getCountry().equalsIgnoreCase(country) && hotel.getCity().equalsIgnoreCase(city)) {
                filteredHotels.add(hotel);
            }
        }
        return filteredHotels;
    }

    /**
     * Filter by average rating.
     * @param minRating - the minimum viable rating of a hotel.
     * @return - list of hotels that qualify.
     */
    public List<Hotel> filterByRating(double minRating) {
        List<Hotel> filteredHotels = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            if (hotel.getAverageRating() >= minRating) {
                filteredHotels.add(hotel);
            }
        }
        return filteredHotels;
    }
}
