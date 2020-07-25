package parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private final List<Car> cars ;
    private final int capacity;
    private int currentSize;

    public ParkingLot() {
        this.currentSize = 0;
        this.capacity = 10;
        this.cars = new ArrayList<>(capacity);
    }
    public ParkingLot(int capacity) {
        this.currentSize = 0;
        this.capacity = capacity;
        this.cars = new ArrayList<>(capacity);
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

    public double getAvailablePositionRate(){
        return 0;
    }
}
