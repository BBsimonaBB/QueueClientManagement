import java.io.FileWriter;
import java.util.List;

public interface Strategy {
    public void addTask(List<Cons> queues, Client c) throws InterruptedException;

    public float getMaxWaitingTime();

    public float getServiceTime();

    public int getPeakHour();

    public enum SelectionPolicy {
        SHORTEST_QUEUE, SHORTEST_TIME;
    }
}
