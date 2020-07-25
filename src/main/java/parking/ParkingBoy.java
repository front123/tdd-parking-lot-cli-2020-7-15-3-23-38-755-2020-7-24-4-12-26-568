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
        boolean isParkingLot2Full = parkingLot2 == null || parkingLot2.isFull();
        if (car == null || (parkingLot.isFull() && isParkingLot2Full) ){
            return null;
        }
        Ticket ticketResult;
        if(parkingLot2 == null || parkingLot.getCurrentSize() <= parkingLot2.getCurrentSize()){
            ticketResult = parkingLot.parking(car);
        }else {
            ticketResult = parkingLot2.parking(car);
        }
        return ticketResult;
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
        boolean isParkingLot2Full = parkingLot2 == null || parkingLot2.isFull();
        if (!parkingLot.isFull() || !isParkingLot2Full){
            return null;
        }
        return "Not enough position.";
    }
}
