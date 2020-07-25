package parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private final ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket parking(Car car){
        if (car == null || parkingLot.isFull() || parkingLot.parking(car)==null ){
            return null;
        }
        return new Ticket(1);
    }

    public Car fetchCar(Ticket ticket) {
        for (Car car: parkingLot.getCars()) {
            if(ticket!=null && ticket.isValid() && car.getId() == ticket.getId()){
                ticket.setValid(false);
                return car;
            }
        }
        return null;
    }
}
