package chapter21;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car {
    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true;
        notify();
    }

    public synchronized void buffed() {
        waxOn = false;
        notify();
    }

    public synchronized void waitForWaxing() throws Exception {
        synchronized (this) {
            while (waxOn == false) {
                wait();
            }
        }
    }

    public synchronized void waitForBuffing() throws Exception {
        synchronized (this) {
            while (waxOn == true) {
                wait();
            }
        }
    }
}


class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Wax on!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Ending Wax on task");
    }

}


class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("Wax Off");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


public class Exercise23 {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exService = Executors.newCachedThreadPool();
        exService.execute(new WaxOn(car));
        exService.execute(new WaxOff(car));

        TimeUnit.MILLISECONDS.sleep(5000);

        exService.shutdownNow();
    }
}
