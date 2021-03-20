package com.gridnine.testing;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class OnEarthMoreThenTwoHoursFilterImplTest {

    private List<Flight> flightList = new LinkedList<>(FlightBuilder.createFlights());
    private FlightTraffic flightTraffic = new FlightAdapter(flightList);

    @Test
    public void filter() {
        List<Flight> expected = new LinkedList<>(flightList);
        expected.remove(4);
        expected.remove(4);
        FlightTraffic flightTraffic = new FlightAdapter(flightList);
        flightTraffic = new OnEarthMoreThenTwoHoursFilterImpl(flightTraffic);
        flightTraffic.filter();
        List<Flight> actual = new LinkedList<>(flightTraffic.getFlights());
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getMinutesOnEarthFromFlight() {
        OnEarthMoreThenTwoHoursFilterImpl onEarthMoreThenTwoHoursFilter= new OnEarthMoreThenTwoHoursFilterImpl(flightTraffic);
        List <Segment> segments = flightList.get(1).getSegments();
        double expected = 60.0;
        double actual = onEarthMoreThenTwoHoursFilter.getMinutesOnEarthFromFlight(segments);
        Assert.assertEquals(expected,actual,0.1);
    }
}