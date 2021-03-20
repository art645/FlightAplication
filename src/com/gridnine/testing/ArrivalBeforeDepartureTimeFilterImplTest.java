package com.gridnine.testing;


import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;


public class ArrivalBeforeDepartureTimeFilterImplTest {

   private List<Flight> flightList = new LinkedList<>(FlightBuilder.createFlights());
   private FlightTraffic flightTraffic = new FlightAdapter(flightList);

    @Test
    public void filter() {
        List<Flight> expected = new LinkedList<>(flightList);
        expected.remove(3);
        FlightTraffic flightTraffic = new FlightAdapter(flightList);
        flightTraffic = new ArrivalBeforeDepartureTimeFilterImpl(flightTraffic);
        flightTraffic.filter();
        List<Flight> actual = new LinkedList<>(flightTraffic.getFlights());
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void compareFlightTime_returnTrue() {
        ArrivalBeforeDepartureTimeFilterImpl arrivalBeforeDepartureTimeFilter = new ArrivalBeforeDepartureTimeFilterImpl(flightTraffic);
        List <Segment> segments = flightList.get(3).getSegments();
        boolean expected = arrivalBeforeDepartureTimeFilter.compareFlightTime(segments);
        boolean actual = true;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void compareFlightTime_returnFalse() {
        ArrivalBeforeDepartureTimeFilterImpl arrivalBeforeDepartureTimeFilter = new ArrivalBeforeDepartureTimeFilterImpl(flightTraffic);
        List <Segment> segments = flightList.get(4).getSegments();
        boolean expected = arrivalBeforeDepartureTimeFilter.compareFlightTime(segments);
        boolean actual = false;
        Assert.assertEquals(expected,actual);
    }
}