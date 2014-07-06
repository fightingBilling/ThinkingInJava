package chapter21;

import java.util.concurrent.TimeUnit;

class NoTask {
    public static void longTimeMethod() throws Exception {
        TimeUnit.SECONDS.sleep(60);
    }
}


class Task implements Runnable {

    @Override
    public void run() {
        try {
            NoTask.longTimeMethod();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}


public class Exercise18 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
