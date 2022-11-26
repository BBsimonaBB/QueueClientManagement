import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        BlockingQueue q = new ArrayBlockingQueue(100);
        Prod p = new Prod(q);
        Thread t1 = new Thread(p);
        t1.start();

        //Cons c = new Cons(q,);
        //Thread t2 = new Thread(c);
        //t2.start();

    }
}
