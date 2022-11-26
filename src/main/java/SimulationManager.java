import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationManager implements Runnable {
    //data ream from UI
    FileWriter myFile;
    boolean done;
    public int timeLimit = 30;
    public int maxProcessingTime = 3;
    public int minProcessingTime = 3;
    public int numberOfQueues = 2;
    public int numberOfClients = 5;
    public int minArrivalTime = 2;
    public int maxArrivalTime = 4;
    public Strategy.SelectionPolicy selectionPolicy = Strategy.SelectionPolicy.SHORTEST_TIME;

    private Scheduler scheduler;
    //frame for displaying simulation

    private SimulationView view;
    private SimulationView2 view2;
    private List<Client> generatedCLients = new ArrayList<>();

    public SimulationManager() {
        view = new SimulationView();
        view.setVisible(true);
        view.addGenerateListener(new generateListener());
        try {
            myFile = new FileWriter("file.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initialize() throws InterruptedException {
        //chestii de facut
        generateNRandomCLients();
        done = true;
        scheduler = new Scheduler(numberOfQueues, myFile, view2, timeLimit, 300);
        scheduler.changeStrategy(selectionPolicy);
    }

    public void generateNRandomCLients() {
        //generate clients and time and blabla
        //sort list to respect to arrival time
        for (int i = 0; i < numberOfClients; i++) {
            Client c = new Client(i, ThreadLocalRandom.current().nextInt(minArrivalTime, maxArrivalTime + 1), ThreadLocalRandom.current().nextInt(minProcessingTime, maxProcessingTime + 1));
            generatedCLients.add(c);
            Collections.sort(generatedCLients);
        }
        view2 = new SimulationView2(numberOfClients, numberOfQueues, generatedCLients);
        view2.setVisible(true);
        for (Client c : generatedCLients) {
            System.out.println(c);
            view2.addThingsToScroll("generatedClients", c);
        }

    }

    class generateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean noError = false;
            try {
                timeLimit = Integer.parseInt(view.getSimulationPeriod().getText());
                numberOfClients = Integer.parseInt(view.getNrOfClients().getText());
                numberOfQueues = Integer.parseInt(view.getNrOfQueues().getText());
                maxProcessingTime = Integer.parseInt(view.getMaxServiceTime().getText());
                minProcessingTime = Integer.parseInt(view.getMinServiceTime().getText());
                minArrivalTime = Integer.parseInt((view.getMinArrivalTime().getText()));
                maxArrivalTime = Integer.parseInt((view.getMaxArrivalTime().getText()));
                view.setVisible(false);
                noError = true;
            } catch (NumberFormatException nfex) {
                view.showError("Un numar introdus e invalid sau ati uitat sa introduceti ceva undeva. Corectati !");
                noError = false;
            }
            if (minArrivalTime > maxArrivalTime || minProcessingTime > maxProcessingTime) {
                view.showError("Un minim undeva e mai mare ca maximum aferent. Corectati !");
                noError = false;
                view.setVisible(true);
            }
            if (noError) {
                try {
                    initialize();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public List<Client> getGeneratedCLients() {
        return generatedCLients;
    }

    @Override
    public void run() {
        int currentTime = 0;
        try {
            while (!done)
                Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 0;
        while (currentTime < timeLimit) {
            if (!generatedCLients.isEmpty()) {
                while (generatedCLients.get(0).getArrivalTime() == currentTime) {
                    try {
                        scheduler.dispatchClient(generatedCLients.get(0));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    generatedCLients.remove(0);
                    view2.removeThingsFromScroll("");
                    ;
                    i++;
                    if (generatedCLients.isEmpty())
                        break;
                }
            }
            currentTime++;
            //System.out.println("Timer: " + currentTime);
            try {
                myFile.write("Timer:  " + currentTime + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            view2.getjTime().setText(currentTime + "");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            myFile.write("WaitingTime mediu: " + scheduler.getStrategy().getMaxWaitingTime() / (float) numberOfClients + "\n");
            myFile.write("ServiceTime mediu: " + scheduler.getStrategy().getServiceTime() / (float) numberOfClients + "\n");
            myFile.write("Peakhour:" + scheduler.getStrategy().getPeakHour() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            myFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        SimulationManager gen = new SimulationManager();
        Thread t = new Thread(gen);
        t.start();

    }
}
