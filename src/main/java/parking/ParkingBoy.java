package parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private final ParkingLot parkingLot2;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.parkingLot2 = null;
    }

    public ParkingBoy(ParkingLot...parkingLots) {
        this.parkingLot = parkingLots[0];
        this.parkingLot2 = parkingLots[1];
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

    public String fetchCarForFeedback(Ticket ticket) {
        if (ticket == null){
            return "Please provide your parking ticket.";
        }
        return "Unrecognized parking ticket.";
    }

    public String parkingForFeedback(Car car) {
        return "Not enough position.";
    }
}
