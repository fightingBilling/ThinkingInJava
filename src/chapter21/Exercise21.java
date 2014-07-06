package chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ERunnableOne implements Runnable {

    @Override
    public void run() {
        System.out.println("wait begin");

        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("wait end");
    }

}


class ERunnableTwo implements Runnable {
    private ERunnableOne eone;

    public ERunnableTwo(ERunnableOne eo) {
        this.eone = eo;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            synchronized (eone) {
                System.out.println("notify for waiting task");
                eone.notifyAll();
            }
        }
    }

}


public class Exercise21 {
    public static void main(String[] args) {
        ERunnableOne eo = new ERunnableOne();
        ERunnableTwo et = new ERunnableTwo(eo);

        ExecutorService exService = Executors.newCachedThreadPool();
        exService.execute(eo);
        exService.execute(et);
        exService.shutdown();
    }
}
