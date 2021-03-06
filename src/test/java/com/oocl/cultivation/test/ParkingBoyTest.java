package com.oocl.cultivation.test;

import exception.NotEnoughPositionException;
import exception.NullTicketException;
import exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import parking.*;

import java.util.ArrayList;
import java.util.List;

class ParkingBoyTest {

    private final ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
    private final ParkingLot parkingLot2 = Mockito.mock(ParkingLot.class);
    @Test
    void should_return_a_ticket_when_parking_given_a_car() throws NotEnoughPositionException {
        //given
        Car car = new Car(1);
        Ticket ticketExpected = new Ticket(1);
        Mockito.when(parkingLot.isFull()).thenReturn(false);
        Mockito.when(parkingLot.parking(car)).thenReturn(ticketExpected);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Ticket ticket = parkingBoy.parking(car);

        //then
        Assertions.assertEquals(ticketExpected, ticket);
    }

    @Test
    void should_return_a_car_when_fetch_car_given_a_ticket_and_a_parking_boy() throws NullTicketException, UnrecognizedParkingTicketException {
        //given
        Ticket ticket = new Ticket(1);
        Car car1 = new Car(1);
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        Mockito.when(parkingLot.getCars()).thenReturn(cars);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Car car = parkingBoy.fetchCar(ticket);

        //then
        Assertions.assertEquals(car1, car);
    }

    @Test
    void should_return_correspond_car_when_parking_given_parking_lot_with_2_cars_and_a_ticket() throws NullTicketException, UnrecognizedParkingTicketException {
        //given
        Car car1 = new Car(1);
        Car car2 = new Car(2);
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        Mockito.when(parkingLot.getCars()).thenReturn(cars);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket ticket = new Ticket(2);

        //when
        Car correspondCar = parkingBoy.fetchCar(ticket);

        //then
        Assertions.assertEquals(car2, correspondCar);

    }

    @Test
    void should_return_null_when_fetch_car_given_a_null_ticket() {
        //given
        Car car1 = new Car(1);
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        Mockito.when(parkingLot.getCars()).thenReturn(cars);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Car car ;
        try {
            car = parkingBoy.fetchCar(null);
        }catch (NullTicketException e){
            car = null;
        }catch (UnrecognizedParkingTicketException e){
            car = null;
        }

        //then
        Assertions.assertNull(car);
    }

    @Test
    void should_return_null_when_fetch_car_given_a_already_used_ticket() throws NullTicketException {
        //given
        Ticket ticket = new Ticket(1);
        ticket.setValid(false);
        Car car1 = new Car(1);
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        Mockito.when(parkingLot.getCars()).thenReturn(cars);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Car car;
        try{
            car = parkingBoy.fetchCar(ticket);
        }catch (UnrecognizedParkingTicketException e){
            car = null;
        }

        //then
        Assertions.assertNull(car);
    }

    @Test
    void should_return_null_when_parking_given_a_full_parking_lot_and_a_extra_car() {
        //given
        Mockito.when(parkingLot.isFull()).thenReturn(true);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car(1);

        //when
        Ticket ticket;
        try {
            ticket = parkingBoy.parking(car);
        }catch (NotEnoughPositionException e){
            ticket = null;
        }

        //then
        Assertions.assertNull(ticket);
    }

    @Test
    void should_return_null_when_parking_given_a_parked_car() throws NotEnoughPositionException {
        //given
        Mockito.reset(parkingLot);
        Car car = new Car(1);
        Mockito.when(parkingLot.isFull()).thenReturn(false);
        Mockito.when(parkingLot.parking(car)).thenReturn(new Ticket(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);


        //when
        Ticket ticketFirst = parkingBoy.parking(car);
        Mockito.when(parkingLot.parking(car)).thenReturn(null);
        Ticket ticket = parkingBoy.parking(car);

        //then
        Assertions.assertNotNull(ticketFirst);
        Assertions.assertNull(ticket);
    }

    @Test
    void should_return_null_when_parking_given_a_null_car() throws NotEnoughPositionException {
        //given
        Mockito.reset(parkingLot);
        Mockito.when(parkingLot.isFull()).thenReturn(false);
        Car car = null;
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Ticket ticket = parkingBoy.parking(car);

        //then
        Assertions.assertNull(ticket);
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_fetch_for_feedback_given_a_used_ticket() {
        //given
        Ticket ticket = new Ticket(1);
        ticket.setValid(false);
        Mockito.reset(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Throwable throwable = Assertions.assertThrows(UnrecognizedParkingTicketException.class, ()->parkingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals("Unrecognized parking ticket.", throwable.getMessage());

    }

    @Test
    void should_return_provide_ticket_message_when_fetch_for_feedback_given_null_ticket() {
        //given
        Ticket ticket = null;
        Mockito.reset(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Throwable throwable = Assertions.assertThrows(NullTicketException.class, () -> parkingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals("Please provide your parking ticket.", throwable.getMessage());
    }

    @Test
    void should_return_not_enough_position_when_parking_for_feedback_given_a_car_and_a_full_parking_lot() {
        //given
        Car car = new Car(1);
        Mockito.when(parkingLot.isFull()).thenReturn(true);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        Throwable throwable = Assertions.assertThrows(NotEnoughPositionException.class, ()->parkingBoy.parking(car));
        //then
        Assertions.assertEquals("Not enough position.", throwable.getMessage());
    }

    @Test
    void should_return_ticket_when_parking_given_a_car_a_full_parking_lot_a_not_full_parking_lot() throws NotEnoughPositionException {
        //given
        Car car = new Car(1);
        Mockito.when(parkingLot.isFull()).thenReturn(true);
        Mockito.when(parkingLot2.isFull()).thenReturn(false);
        Mockito.when(parkingLot.getCurrentSize()).thenReturn(10);
        Mockito.when(parkingLot2.getCurrentSize()).thenReturn(1);
        Mockito.when(parkingLot.parking(car)).thenReturn(null);
        Mockito.when(parkingLot2.parking(car)).thenReturn(new Ticket(1));
        Mockito.when(parkingLot2.parking(car)).thenReturn(new Ticket(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot, parkingLot2);

        //when
        Ticket ticket = parkingBoy.parking(car);

        //then
        Assertions.assertEquals(1, ticket.getId());
    }


    @Test
    void should_parking_into_more_empty_position_parking_lot_when_parking_given_two_parking_lot_in_different_empty_position() {
        //given
        Car car = new Car(1);
        Ticket ticket1 = new Ticket(1, "parking lot 1");
        Ticket ticket2 = new Ticket(1, "parking lot 2");
        Mockito.when(parkingLot.getEmptyPositionSize()).thenReturn(2);
        Mockito.when(parkingLot2.getEmptyPositionSize()).thenReturn(3);
        Mockito.when(parkingLot.parking(car)).thenReturn(ticket1);
        Mockito.when(parkingLot2.parking(car)).thenReturn(ticket2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot, parkingLot2);

        //when
        Ticket ticket = smartParkingBoy.parking(car);

        //then
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(ticket2.getParkingLotName(), ticket.getParkingLotName());
    }

    @Test
    void should_return_ticket_with_parking_lot1_when_parking_given_two_parking_lot_with_different_available_position_rate() {
        //given
        Car car = new Car(1);
        Ticket ticket1 = new Ticket(1, "parking lot 1");
        Ticket ticket2 = new Ticket(1, "parking lot 2");
        Mockito.when(parkingLot.parking(car)).thenReturn(ticket1);
        Mockito.when(parkingLot2.parking(car)).thenReturn(ticket2);
        Mockito.when(parkingLot.getAvailablePositionRate()).thenReturn(0.3);
        Mockito.when(parkingLot2.getAvailablePositionRate()).thenReturn(0.2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot, parkingLot2);

        //when
        Ticket ticket = superSmartParkingBoy.parking(car);

        //then
        Assertions.assertEquals(ticket1.getParkingLotName(), ticket.getParkingLotName());

    }
}
