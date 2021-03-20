package com.gridnine.testing;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Delete flights where time on earth between arrival time of segment
 * and departure time of next segment is more then two hours.
 */
public class OnEarthMoreThenTwoHoursFilterImpl implements FlightFilter{

    private FlightTraffic flightTraffic;

    public OnEarthMoreThenTwoHoursFilterImpl(FlightTraffic flightTraffic) {
        this.flightTraffic = flightTraffic;
    }

    @Override
    public void filter() {
        LinkedList<Flight> flightsLinkedList = new LinkedList<>(flightTraffic.getFlights());
        Iterator<Flight> flightIterator = flightsLinkedList.iterator();
        while (flightIterator.hasNext()) {
            double minutesOnEarth = getMinutesOnEarthFromFlight(flightIterator.next().getSegments());
            if (minutesOnEarth > 120) {
                flightIterator.remove();
            }
        }
        flightTraffic.setFlights(flightsLinkedList);
    }

    public double getMinutesOnEarthFromFlight(List<Segment> segments) {
        long millisOnEarth = 0;
        for (int i = 0; i < segments.size() - 1; i++) {
            LocalDateTime firstSegmentArrivalTime = segments.get(i).getArrivalDate();
            LocalDateTime secondSegmentDepartureTime = segments.get(i + 1).getDepartureDate();
            long millisAmount = Timestamp.valueOf(secondSegmentDepartureTime).getTime() - Timestamp.valueOf(firstSegmentArrivalTime).getTime();
            if (millisAmount > 0) {
                millisOnEarth += millisAmount;
            }
        }
            double minutesOnEarth = millisOnEarth / 60000;
            return minutesOnEarth;
    }

    @Override
    public void print() {
        System.out.println("______Исключены полёты где общее время проведенное на земле за весь полёт более двух часов_________");
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
