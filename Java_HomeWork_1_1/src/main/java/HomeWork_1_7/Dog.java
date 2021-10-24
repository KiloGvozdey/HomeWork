package HomeWork_1_7;

public class Dog extends Animal{

    private static final int MAX_RUN_DISTANCE = 200;
    private static final int MAX_SWIM_DISTANCE = -1;
    public static int count;

    public Dog(String name, int maxSwimDistance, int maxRunDistance) {
        super(MAX_SWIM_DISTANCE, MAX_RUN_DISTANCE);
        this.name = name;
    }
    public Dog(String name){
        this(name, MAX_SWIM_DISTANCE, MAX_RUN_DISTANCE);
        count++;
    }

    @Override
    void printAnimalIsFull() {

    }
    @Override
    public void eat(Plate plate){}

    @Override
    void swim(int distance) {
        if(distance <= getMaxSwimDistance()){
        System.out.printf("%s проплыл %s метров.%n", name, distance);
        }
        else {
            System.out.printf("%s столько не проплывет.%n", name);
        }
    }

    @Override
    void run(int distance) {
        if(distance <= getMaxRunDistance()) {
            System.out.printf("%s пробежал %s метров.%n", name, distance);
        }
        else {
            System.out.printf("%s столько не пробежит.%n", name);
        }
    }
}
