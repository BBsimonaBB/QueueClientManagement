import java.util.concurrent.BlockingQueue;
import java.util.Random;

public class Prod implements Runnable {
    private BlockingQueue q;

    public Prod(BlockingQueue q) {
        this.q = q;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Client c = new Client(i, random.nextInt(10), random.nextInt(10));
            try {
                q.put(c);
                System.out.println("Pun clientul " + c);
                // Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
