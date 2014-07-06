package chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LiftOff implements Runnable {
    protected int countDown = 5000;
    private static int taskCount;
    private final int id = taskCount++;

    public LiftOff() {

    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + ").";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted:#" + id);
                return;
            }
            System.out.println(status());
            Thread.yield();
        }
    }
}


public class Exercise20 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }

        Thread.yield();
        exec.shutdownNow();
    }
}
