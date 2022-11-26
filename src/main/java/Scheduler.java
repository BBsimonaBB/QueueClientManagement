import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Scheduler {
    int timeLimit;
    FileWriter myFile;
    SimulationView2 view2;
    private List<Cons> queues = new ArrayList<>();
    //private int maxQueues;
    private int maxClientsPerQueue;
    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public Scheduler(int maxQueues, FileWriter myFile, SimulationView2 view2, int timeLimit, int maxClientsPerQueue) {
        //this.maxQueues = maxQueues;
        this.timeLimit = timeLimit;
        this.myFile = myFile;
        this.view2 = view2;
        AtomicInteger zero = new AtomicInteger(0);
        for (int i = 0; i < maxQueues; i++) {
            Cons server = new Cons(new ArrayBlockingQueue(100), zero, myFile, view2, timeLimit);
            queues.add(server);
            Thread t = new Thread(server);
            t.start();
        }

    }

    public void changeStrategy(Strategy.SelectionPolicy policy) {
        //policy = si aici i zic eu pe care sa mearga..ar tbr sa mearga pe amandoua
        if (policy == Strategy.SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteStrategyQueue(myFile, view2);

        if (policy == Strategy.SelectionPolicy.SHORTEST_TIME)
            strategy = new ConcreteStrategyTime(myFile, view2);
    }

    public void dispatchClient(Client c) throws InterruptedException {
        strategy.addTask(queues, c);
    }

    public List<Cons> getQueues() {
        return queues;
    }
}
