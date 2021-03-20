package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * FlightFilterImpl that delete flights where arrival time of segment is before departure time
 */
public class ArrivalBeforeDepartureTimeFilterImpl implements FlightFilter {

    private FlightTraffic flightTraffic;

    public ArrivalBeforeDepartureTimeFilterImpl(FlightTraffic flightTraffic) {
        this.flightTraffic = flightTraffic;
    }

    @Override
    public void filter() {
        LinkedList<Flight> flightsLinkedList = new LinkedList<>(flightTraffic.getFlights());
        Iterator<Flight> flightIterator = flightsLinkedList.iterator();
        while (flightIterator.hasNext()) {
            boolean isDepatureBeforeNow = compareFlightTime(flightIterator.next().getSegments());
            if (isDepatureBeforeNow) {
                flightIterator.remove();
            }
        }
        flightTraffic.setFlights(flightsLinkedList);
        flightTraffic.filter();
    }
    //
    public boolean compareFlightTime(List<Segment> segments) {
        for (Segment segment : segments) {
            LocalDateTime departureDate = segment.getDepartureDate();
            LocalDateTime arrivalDate = segment.getArrivalDate();
            if (arrivalDate.isBefore(departureDate)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void print() {

        System.out.println("______Исключены полёты где имеются сегменты с датой прилёта раньше даты вылета_________");
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