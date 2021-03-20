package com.gridnine.testing;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class DepartureUntilNowFilterImplTest {

    private List<Flight> flightList = new LinkedList<>(FlightBuilder.createFlights());
    private FlightTraffic flightTraffic = new FlightAdapter(flightList);

    @Test
    public void filter() {
        List<Flight> expected = new LinkedList<>(flightList);
        expected.remove(2);
        FlightTraffic flightTraffic = new FlightAdapter(flightList);
        flightTraffic = new DepartureUntilNowFilterImpl(flightTraffic);
        flightTraffic.filter();
        List<Flight> actual = new LinkedList<>(flightTraffic.getFlights());
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void compareFlightTime_returnTrue() {
        DepartureUntilNowFilterImpl departureUntilNowFilter = new DepartureUntilNowFilterImpl(flightTraffic);
        List <Segment> segments = flightList.get(2).getSegments();
        LocalDateTime nowTime = LocalDateTime.now();
        boolean expected = departureUntilNowFilter.compareFlightTime(segments,nowTime);
        boolean actual = true;
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void compareFlightTime_returnFalse() {
        DepartureUntilNowFilterImpl departureUntilNowFilter = new DepartureUntilNowFilterImpl(flightTraffic);
        List <Segment> segments = flightList.get(3).getSegments();
        LocalDateTime nowTime = LocalDateTime.now();
        boolean expected = departureUntilNowFilter.compareFlightTime(segments,nowTime);
        boolean actual = false;
        Assert.assertEquals(expected,actual);
    }
}