package parking;

import exception.NotEnoughPositionException;
import exception.NullTicketException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {

    private final List<ParkingLot> parkingLots = new ArrayList<>();


    public ParkingBoy(ParkingLot...parkingLots) {
        this.parkingLots.addAll(Arrays.asList(parkingLots));
    }

    public Ticket parking(Car car) throws NotEnoughPositionException {
        if (car == null){
            return null;
        }
        boolean isAllParkingLotFull = true;
        Ticket ticketResult = null;
        for (ParkingLot parkingLot: parkingLots) {
            if(!parkingLot.isFull()){
                isAllParkingLotFull = false;
                ticketResult = parkingLot.parking(car);
                break;
            }
        }
        if(isAllParkingLotFull){
            throw new NotEnoughPositionException("Not enough position.");
        }

        return ticketResult;
    }

    public Car fetchCar(Ticket ticket) throws NullTicketException {
        if (ticket == null){
            throw new NullTicketException("Please provide your parking ticket.");
        }
        for (ParkingLot parkingLot: parkingLots) {
            for (Car car: parkingLot.getCars()) {
                if(ticket.isValid() && car.getId() == ticket.getId()){
                    ticket.setValid(false);
                    return car;
                }
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

    public List<ParkingLot> getParkingLots(){
        return parkingLots;
    }
}
