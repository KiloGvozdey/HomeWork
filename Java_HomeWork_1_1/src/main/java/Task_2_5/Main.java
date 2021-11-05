package Task_2_5;

import java.util.Arrays;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        doDoubleThread();
        Thread.sleep(2000);
        doSingleThread();
    }
    synchronized static void doSingleThread(){
        final int size = 10000000;
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long finish = System.currentTimeMillis();
        System.out.println("Время в однопотоке составило: " + (finish - start) + " миллисекунд");

            }

    synchronized static void doDoubleThread(){
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        long start = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        Thread t0 = new Thread(()->{
            System.arraycopy(arr, 0, a1, 0, h);
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(a1, 0, arr, 0, h);
        });

        Thread t1 = new Thread(()->{
            System.arraycopy(arr, h, a2, 0, h);
            for (int i = 0; i < a2.length; i++) {
                a2[i] = (float)(a2[i] * Math.sin(0.2f + (i + h) / 5) * Math.cos(0.2f + (i + h) / 5) * Math.cos(0.4f + (i + h) / 2));
            }
            System.arraycopy(a2, 0, arr, h, h);
        });

        t0.start();
        t1.start();
        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long finish = System.currentTimeMillis();
        System.out.println("Время в многопотоке составило: " + (finish - start) + " миллисекунд");
    }
}
