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
}
