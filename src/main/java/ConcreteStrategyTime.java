import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyTime implements Strategy {
    private float maxWaitingTime = 0.0f;
    private float ServiceTime = 0.0f;
    private int maxNrClients;
    private int peakHour;
    FileWriter myFile;
    SimulationView2 view2;

    public float getMaxWaitingTime() {
        return maxWaitingTime;
    }

    public float getServiceTime() {
        return ServiceTime;
    }

    public int getPeakHour() {
        return peakHour;
    }

    public ConcreteStrategyTime(FileWriter myFile, SimulationView2 view2) {
        this.view2 = view2;
        this.myFile = myFile;
    }

    @Override
    public void addTask(List<Cons> queues, Client c) throws InterruptedException {
        int poz = -1;
        int minTime = 10000;
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
                if (queues.get(j).getWaitingPeriod().get() < minTime) {
                    minTime = queues.get(j).getWaitingPeriod().get();
                    //minTime = timpul cel mai mic la care poate fi preluat acest client!
                    poz = j;
                }
            c.setArrivalTime(minTime);
        }
        maxWaitingTime = maxWaitingTime + c.getArrivalTime() + c.getServiceTime();
        ServiceTime = ServiceTime + c.getServiceTime();
        queues.get(poz).addClient(c);
        queues.get(poz).setWaitingPeriod(new AtomicInteger(c.getServiceTime() + c.getArrivalTime()));
        try {
            myFile.write("Pun in coada " + poz + " pe \n " + c + "\n");
            view2.addThingsToScroll("Coada " + poz, c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("Pun clientul cu id " + c.getID() + " in coada " + poz);
    }
}
