package com.oocl.cultivation.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parking.Car;
import parking.ParkingBoy;

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
}
