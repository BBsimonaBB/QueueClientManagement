import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyQueue implements Strategy {
    private float maxWaitingTime = 0.0f;
    private float ServiceTime = 0.0f;
    private int maxNrClients;
    private int peakHour;
    FileWriter myFile;
    SimulationView2 view2;

    public ConcreteStrategyQueue(FileWriter myFile, SimulationView2 view2) {
        this.view2 = view2;
        this.myFile = myFile;
    }

    public float getMaxWaitingTime() {
        return maxWaitingTime;
    }

    public int getPeakHour() {
        return peakHour;
    }

    public float getServiceTime() {
        return ServiceTime;
    }

    @Override
    public void addTask(List<Cons> queues, Client c) throws InterruptedException {
        int poz = -1;
        int minPeople = 10000;
        for (int i = 0; i < queues.size(); i++) {
            if (queues.get(i).getWaitingPeriod().get() <= c.getArrivalTime()) {
                poz = i;
                break;
            }
        }
        for (Cons server : queues) {
            if (server.getQ().size() > maxNrClients) {
                maxNrClients = server.getQ().size();
                peakHour = c.getArrivalTime();
            }
        }
        if (poz == -1) {
            for (int j = 0; j < queues.size(); j++)
                if (queues.get(j).getQ().size() < minPeople) {
                    minPeople = queues.get(j).getQ().size();
                    poz = j;
                }
            Client lastPerson = null;
            for (Client client : queues.get(poz).getQ())
                lastPerson = client;
            c.setArrivalTime(lastPerson.getArrivalTime() + lastPerson.getServiceTime());
        }
        queues.get(poz).addClient(c);
        queues.get(poz).setWaitingPeriod(new AtomicInteger(c.getServiceTime() + c.getArrivalTime()));
        try {
            myFile.write("Pun in coada " + poz + " pe \n " + c + "\n");
            view2.addThingsToScroll("Coada " + poz, c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
