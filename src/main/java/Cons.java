import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Cons implements Runnable {
    int timeLimit;
    FileWriter myFile;
    SimulationView2 view2;
    private BlockingQueue<Client> q;
    private AtomicInteger waitingPeriod;

    public Cons(BlockingQueue q, AtomicInteger waitingPeriod, FileWriter myFile, SimulationView2 view2, int timeLimit) {
        this.timeLimit = timeLimit;
        this.q = q;
        this.waitingPeriod = waitingPeriod;
        this.myFile = myFile;
        this.view2 = view2;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public BlockingQueue<Client> getQ() {
        return q;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public void addClient(Client c) throws InterruptedException {
        q.put(c);
    }

    @Override
    public void run() {

        while (true) {
            try {
                if (q.peek() != null) {
                    Client c = q.peek();
                    Thread.sleep(c.getServiceTime() * 1000L);
                    if (timeLimit < c.getArrivalTime() + c.getServiceTime())
                        break;
                    myFile.write("Am scos clientul cu ID-ul " + c.getID() + "\n");
                    view2.removeThingsFromScroll("ID = " + c.getID());
                    q.take();
                }
            } catch (InterruptedException | IOException e) {
                Thread.interrupted();
                e.printStackTrace();
            }
        }

    }

}
