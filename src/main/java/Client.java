public class Client implements Comparable<Client> {
    private final int ID;
    private int arrivalTime;
    private final int serviceTime;

    public Client(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ID=" + ID +
                ", arrivalTime=" + arrivalTime +
                ", serviceTime=" + serviceTime +
                '}';
    }

    @Override
    public int compareTo(Client o) {
        return Integer.compare(this.arrivalTime, o.arrivalTime);
    }
}
