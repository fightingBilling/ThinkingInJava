package chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Meal {
    private final int orderNum;

    public Meal(int num) {
        this.orderNum = num;
    }

    public String toString() {
        return "Meal " + orderNum;
    }
}


class Restaurant {
    Meal meal = null;
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);
    BusBoy busBoy = new BusBoy(this);
    ExecutorService exec = Executors.newCachedThreadPool();
    
    public Restaurant() {
        exec.execute(waitPerson);
        exec.execute(busBoy);
        exec.execute(chef);
    }
    
}


class WaitPerson implements Runnable {
    private Restaurant restaurant;
    boolean notified;

    public WaitPerson(Restaurant r) {
        this.restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        wait();
                    }
                }
                System.out.println("WaitPerson got " + restaurant.meal);

                synchronized (restaurant.busBoy) {
                    restaurant.busBoy.notified = true;
                    restaurant.busBoy.meal = restaurant.meal;
                    restaurant.busBoy.notifyAll();
                }


                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }

                synchronized (this) {
                    if (!notified) {
                        wait();
                    }
                    notified = false;
                }

            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson interrupted!");
        }

    }
}


class BusBoy implements Runnable {
    private Restaurant restaurant;
    boolean notified;
    volatile Meal meal;

    public BusBoy(Restaurant r) {
        restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    if (!notified) {
                        wait();
                    }
                    notified = false;
                }

                System.out.println("Busboy cleaned up " + meal);

                synchronized (restaurant.waitPerson) {
                    restaurant.waitPerson.notified = true;
                    restaurant.waitPerson.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("BusBoy interrupted");
        }
    }

}


class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;
    public Chef(Restaurant r) {
        this.restaurant = r;
    }
    
    @Override
    public void run() {
        try {
            while (! Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) {
                        wait();
                    }
                }
                
                if (++ count == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                
                System.out.println("Order up!");
                
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted!");
        }
    }
}


public class Exercise26 {
    public static void main(String[] args) {
        new Restaurant();
    }
}
