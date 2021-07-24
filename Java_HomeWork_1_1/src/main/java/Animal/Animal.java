public abstract class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    abstract void swim(int distance);
    abstract void run(int distance);
}
