package parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private final List<Car> parkingLot;

    public ParkingBoy() {
        parkingLot = new ArrayList<>();
    }
    public ParkingBoy(List<Car> parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String parking(Car car){
        return "1";
    }

    public Car fetchCar(String ticket) {
        for (Car car: parkingLot) {
            if(String.valueOf(car.getId()).equals(ticket)){
                return car;
            }
        }
        return null;
    }
}
