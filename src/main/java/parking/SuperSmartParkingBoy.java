package parking;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket parking(Car car) {
        boolean isAllParkingLotFull = true;
        Ticket ticketResult = null;
        for (ParkingLot parkingLot: getParkingLots()) {
            if(!parkingLot.isFull()){
                isAllParkingLotFull = false;
                break;
            }
        }
        if(isAllParkingLotFull){
            // throw message: Not enough position.
            return null;
        }
        ParkingLot parkingLotWithLargerEmptyPosition = getParkingLots().stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(Comparator.comparingDouble(ParkingLot::getAvailablePositionRate))
                .get();
        return parkingLotWithLargerEmptyPosition.parking(car);
    }
}
