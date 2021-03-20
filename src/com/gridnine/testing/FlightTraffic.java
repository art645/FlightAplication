package com.gridnine.testing;

import java.util.List;

/**
 * The main parent interface uses for cast objects
 */
public interface FlightTraffic {
    public void filter();
    public void print();
    public List<Flight> getFlights();
    public void setFlights(List<Flight> flights);
}
