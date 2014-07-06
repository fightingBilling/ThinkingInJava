package chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise22 {
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Runnable r1 = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        return;
                    }

                    flag = true;
                }
            }
        };

        Runnable r2 = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    long startTime = System.currentTimeMillis();

                    while (flag == false) {
                    }

                    long endTime = System.currentTimeMillis();

                    System.out.println("Spend time:" + (endTime - startTime) + "毫秒");
                    flag = false;
                }
            }
        };

        ExecutorService exService = Executors.newCachedThreadPool();
        exService.execute(r1);
        exService.execute(r2);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exService.shutdownNow();
    }
}
