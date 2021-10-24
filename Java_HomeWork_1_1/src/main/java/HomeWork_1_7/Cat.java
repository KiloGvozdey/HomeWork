package HomeWork_1_7;

public class Cat extends Animal{
    private static final int MAX_RUN_DISTANCE = 200;
    private static final int MAX_SWIM_DISTANCE = -1;
    private final String name;
    private final int appetite;
    private boolean isFull = false;



    public int getAppetite() {
        return appetite;
    }


    public static int count;

    public Cat(String name, int appetite, int maxSwimDistance, int maxRunDistance) {
        super(MAX_SWIM_DISTANCE, MAX_RUN_DISTANCE);
        this.name = name;
        this.appetite = appetite;
    }
    public Cat(String name, int appetite){
        this(name, appetite, MAX_SWIM_DISTANCE, MAX_RUN_DISTANCE);
        count++;
    }
    @Override
    public void eat(Plate plate){
        if(appetite <= plate.getFood()) {
            plate.decreaseFood(appetite);
            isFull = true;
        }
    }
    @Override
    public void printAnimalIsFull(){
        if(isFull) System.out.println(name + " is full.");
        else System.out.println(name + " is hungry.");
    }

    @Override
    void swim(int distance) {
        System.out.printf("%s не умеет плавать=(((%n", name);
    }

    @Override
    void run(int distance) {
        if (distance <= getMaxRunDistance()) {
            System.out.printf("%s пробежал %s метров.%n", name, distance);
        }
        else System.out.printf("%s столько не пробежит.%n", name);
    }

}
