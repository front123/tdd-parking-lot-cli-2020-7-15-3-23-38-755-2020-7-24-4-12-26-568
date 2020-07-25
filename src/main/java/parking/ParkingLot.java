package parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private final List<Car> cars ;
    private final int maxSize = 10;
    private int currentSize;

    public ParkingLot() {
        this.currentSize = 0;
        this.cars = new ArrayList<>(maxSize);
    }

    public Ticket parking(Car car){
        return null;
    }

    public List<Car> getCars() {
        return cars;
    }


    public boolean isFull() {
        return false;
    }

    public int getCurrentSize() {
        return currentSize;
    }
}
