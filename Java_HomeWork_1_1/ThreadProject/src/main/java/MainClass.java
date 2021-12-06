import java.util.concurrent.CountDownLatch;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static CountDownLatch countDownLatchStart;
    public static CountDownLatch countDownLatchFinish;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        countDownLatchStart = new CountDownLatch(cars.length);
        countDownLatchFinish = new CountDownLatch(cars.length);
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        countDownLatchStart.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        countDownLatchFinish.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
