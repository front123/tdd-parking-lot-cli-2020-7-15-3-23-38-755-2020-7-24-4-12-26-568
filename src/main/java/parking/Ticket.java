package parking;

public class Ticket {
    private final int id;
    private boolean isValid;
    private String parkingLotName;

    public Ticket(int id) {
        this.id = id;
        this.isValid = true;
    }
    public Ticket(int id, String parkingLotName) {
        this.id = id;
        this.isValid = true;
        this.parkingLotName = parkingLotName;
    }

    public int getId() {
        return id;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

}
