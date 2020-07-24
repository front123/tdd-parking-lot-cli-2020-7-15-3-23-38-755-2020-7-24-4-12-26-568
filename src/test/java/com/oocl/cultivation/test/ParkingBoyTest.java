package com.oocl.cultivation.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import parking.Car;
import parking.ParkingBoy;
import parking.ParkingLot;
import parking.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class ParkingBoyTest {

    private final ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
    @Test
    void should_return_a_ticket_when_parking_given_a_car() {
        //given
        Car car = new Car(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Ticket ticket = parkingBoy.parking(car);

        //then
        Assertions.assertEquals(1, ticket.getId());
    }

    @Test
    void should_return_a_car_when_fetch_car_given_a_ticket_and_a_parking_boy() {
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
        Assertions.assertEquals(1, car.getId());
    }

    @Test
    void should_return_correspond_car_when_parking_given_parking_lot_with_2_cars_and_a_ticket() {
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
        Car car = parkingBoy.fetchCar(null);

        //then
        Assertions.assertNull(car);
    }

    @Test
    void should_return_null_when_fetch_car_given_a_already_used_ticket() {
        //given
        Ticket ticket = new Ticket(1);
        ticket.setValid(false);
        Car car1 = new Car(1);
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        Mockito.when(parkingLot.getCars()).thenReturn(cars);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Car car = parkingBoy.fetchCar(ticket);

        //then
        Assertions.assertNull(car);
    }

}
