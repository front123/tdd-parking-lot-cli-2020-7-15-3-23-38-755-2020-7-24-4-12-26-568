package parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private final ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket parking(Car car){
        return new Ticket(1);
    }

    public Car fetchCar(Ticket ticket) {
        for (Car car: parkingLot.getCars()) {
            if(ticket!=null && car.getId() == ticket.getId()){
                return car;
            }
        }
        return null;
    }
}
