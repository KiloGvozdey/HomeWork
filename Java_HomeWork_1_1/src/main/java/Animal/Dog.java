public class Dog extends Animal{

    public Dog(String name) {
        super(name);
    }

    @Override
    void swim(int distance) {
        if(distance <= 10){
        System.out.printf("%s проплыл %s метров.%n", name, distance);
        }
        else {
            System.out.printf("%s столько не проплывет.%n", name);
        }
    }

    @Override
    void run(int distance) {
        if(distance <= 500) {
            System.out.printf("%s пробежал %s метров.%n", name, distance);
        }
        else {
            System.out.printf("%s столько не пробежит.%n", name);
        }
    }
}
