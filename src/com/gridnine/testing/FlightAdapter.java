package com.gridnine.testing;

import java.util.List;
import java.util.stream.Stream;

/**
 * Class need to cast data from static method FlightBuilder.createFlights() to FlightTraffic type
 */
public class FlightAdapter  implements  FlightTraffic{

    private List<Flight> flights;


    public FlightAdapter(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public void filter() {}

    @Override
    public void print() {
        Stream.of(flights).forEach(System.out::println);
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
