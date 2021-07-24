public class Cat extends Animal{

    public Cat(String name){
        super(name);
    }

    @Override
    void swim(int distance) {
        System.out.printf("%s не умеет плавать=(((%n", name);
    }

    @Override
    void run(int distance) {
        if (distance <= 200) {
            System.out.printf("%s пробежал %s метров.%n", name, distance);
        }
        else System.out.printf("%s столько не пробежит.%n", name);
    }
}
