public abstract class Animal {
    String name;
    private final int maxSwimDistance;
    private final int maxRunDistance;
    public static int count;

    public int getMaxSwimDistance() {
        return maxSwimDistance;
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public Animal(int maxSwimDistance, int maxRunDistance) {
        this.maxSwimDistance = getMaxSwimDistance();
        this.maxRunDistance = getMaxRunDistance();
        count++;
    }
    abstract void printAnimalIsFull();
    abstract void eat(Plate plate);
    abstract void swim(int distance);
    abstract void run(int distance);
}
