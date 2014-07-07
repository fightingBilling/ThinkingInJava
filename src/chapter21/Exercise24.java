package chapter21;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class FlowQueue<T> {
    private Queue<T> queue = new LinkedList<T>();
    private int maxSize;

    public FlowQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void put(T v) throws InterruptedException {
        while (queue.size() >= maxSize) {
            wait();
        }
        queue.offer(v);
        maxSize++;
        notifyAll();
    }

    public synchronized T get() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }

        T returnVal = queue.poll();
        maxSize--;
        notifyAll();

        return returnVal;
    }
}


class Item {
    private static int counter;
    private int id = counter++;

    public String toString() {
        return "Item" + id;
    }
}


class Producer implements Runnable {
    private int delay;
    private FlowQueue<Item> output;

    public Producer(FlowQueue<Item> output, int sleepTime) {
        this.output = output;
        delay = sleepTime;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                output.put(new Item());
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

}


class Consumer implements Runnable {
    private int delay;
    private FlowQueue<?> input;

    public Consumer(FlowQueue<?> input, int sleepTime) {
        this.input = input;
        this.delay = sleepTime;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                System.out.println(input.get());
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

}


public class Exercise24 {
    public static void main(String[] args) throws Exception {
        int prodecerSleep = 200;
        int consumerSleep = 1;

        FlowQueue<Item> fq = new FlowQueue<Item>(100);

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Producer(fq, prodecerSleep));
        exec.execute(new Consumer(fq, consumerSleep));
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
    }
}
