package parking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private final ParkingLot parkingLot2;
    private final List<ParkingLot> parkingLots = new ArrayList<>();


    public ParkingBoy(ParkingLot...parkingLots) {
        ParkingLot parkingLot2Temp;
        this.parkingLot = parkingLots[0];
        try{
            parkingLot2Temp = parkingLots[1];
        }catch (Exception e){
            parkingLot2Temp = null;
        }
        this.parkingLot2 = parkingLot2Temp;
        this.parkingLots.addAll(Arrays.asList(parkingLots));
    }

    public Ticket parking(Car car){
        boolean isParkingLot2Full = parkingLot2 == null || parkingLot2.isFull();
        if (car == null){
            return null;
        }
        if ((parkingLot.isFull() && isParkingLot2Full)){
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
            // throw message: Not enough position.
            return null;
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

    public List<ParkingLot> getParkingLots(){
        return parkingLots;
    }
}
