package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * FlightFilterImpl that delete flights where departure time of segment is before now time
 */
public class DepartureUntilNowFilterImpl implements  FlightFilter{

    private FlightTraffic flightTraffic;

    public DepartureUntilNowFilterImpl(FlightTraffic flightTraffic) {
        this.flightTraffic = flightTraffic;
    }

    @Override
    public void filter() {
        LinkedList<Flight> flightsLinkedList = new LinkedList<>(flightTraffic.getFlights());
        LocalDateTime nowDate = LocalDateTime.now();
        Iterator<Flight> flightIterator = flightsLinkedList.iterator();

        while (flightIterator.hasNext()) {
            boolean isBefore = compareFlightTime(flightIterator.next().getSegments(), nowDate);
            if (isBefore == true) {
                flightIterator.remove();
            }
        }
        flightTraffic.setFlights(flightsLinkedList);
        flightTraffic.filter();
    }

    public boolean compareFlightTime(List<Segment> segments, LocalDateTime nowDate) {
        for (Segment segment : segments) {
            LocalDateTime departureDate = segment.getDepartureDate();
            if (departureDate.isBefore(nowDate)) {
                return true;
            }
        }
        return  false;
    }

    @Override
    public void print() {
        System.out.println("______Исключены вылеты до текущего момента врмени_________");
        flightTraffic.print();
    }

    @Override
    public List<Flight> getFlights() {
        return flightTraffic.getFlights();
    }

    @Override
    public void setFlights(List<Flight> flights) {
        flightTraffic.setFlights(flights);
    }
}
