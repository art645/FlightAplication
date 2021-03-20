package com.gridnine.testing;
import java.util.List;
/**
 * Class that uses our filters
 * For uses filters just wrap FlightAdapter in one or more filters that you need.
 */
public class FlightFilterApi {

    public static void main(String[] args) {
        // get all flights
        List<Flight> flights = FlightBuilder.createFlights();
        FlightTraffic flightTraffic = new FlightAdapter(flights);
        flightTraffic = new ArrivalBeforeDepartureTimeFilterImpl(flightTraffic);
        flightTraffic.filter();
        flightTraffic.print();

        FlightTraffic flightTraffic1 = new FlightAdapter(flights);
        flightTraffic1 = new DepartureUntilNowFilterImpl(flightTraffic1);
        flightTraffic1.filter();
        flightTraffic1.print();

        FlightTraffic flightTraffic2 = new FlightAdapter(flights);
        flightTraffic2 = new OnEarthMoreThenTwoHoursFilterImpl(flightTraffic2);
        flightTraffic2.filter();
        flightTraffic2.print();
    }
}
