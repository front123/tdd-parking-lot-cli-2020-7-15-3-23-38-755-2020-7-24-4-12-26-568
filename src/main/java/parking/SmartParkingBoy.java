package parking;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot parkingLot, ParkingLot parkingLot2) {
        super(parkingLot, parkingLot2);
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
                .max((ParkingLot p1, ParkingLot p2) -> p1.getEmptyPositionSize() - p2.getEmptyPositionSize())
                .get();
        return parkingLotWithLargerEmptyPosition.parking(car);
    }
}
