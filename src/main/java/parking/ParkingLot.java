package parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private final List<Car> cars ;
    private final int maxSize = 10;
    private int currentSize;

    public ParkingLot() {
        this.cars = new ArrayList<>(maxSize);
    }

    public boolean parking(Car car){
        return true;
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }
}
