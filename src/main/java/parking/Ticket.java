package parking;

public class Ticket {
    private final int id;
    private boolean isValid;

    public Ticket(int id) {
        this.id = id;
        this.isValid = true;
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
}
