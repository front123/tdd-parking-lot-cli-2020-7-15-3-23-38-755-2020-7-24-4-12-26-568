package com.oocl.cultivation.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import parking.Car;
import parking.ParkingBoy;

import java.util.LinkedList;
import java.util.List;

class ParkingBoyTest {
    @Test
    void should_return_a_ticket_when_parking_given_a_car() {
        //given
        Car car = new Car(1);
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        String ticket = parkingBoy.parking(car);

        //then
        Assertions.assertEquals("1", ticket);
    }

    @Test
    void should_return_a_car_when_fetch_car_given_a_ticket_and_a_parking_boy() {
        //given
        String ticket = "1";
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        Car car = parkingBoy.fetchCar(ticket);

        //then
        Assertions.assertEquals(1,car.getId());
    }

    @Test
    void should_return_correspond_car_when_parking_given_parking_lot_with_2_cars_and_parking_boy_and_a_ticket() {
        //given
        Car car1 = new Car(1);
        Car car2 = new Car(2);
        List<Car> parkingLot = new LinkedList<>();
        parkingLot.add(car1);
        parkingLot.add(car2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        String ticket = "2";

        //when
        Car correspondCar = parkingBoy.fetchCar(ticket);

        //then
        Assertions.assertEquals(car2, correspondCar);

    }
}
