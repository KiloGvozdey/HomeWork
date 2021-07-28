public class Main {
    public static void main(String[] args) {

        Animal[] animals = {
                new Cat("Barsik", 25),
                new Cat("Snezhok", 10),
                new Cat("Vasya", 12),
                new Cat("Pushok", 30),
                new Cat("Murzik", 9)
        };
        Plate plate = new Plate(25);
        plate.info();
        for (int i = 0; i < animals.length; i++) {
            animals[i].eat(plate);
            animals[i].printAnimalIsFull();
            plate.info();
            plate.increaseFood(15);
        }



    }
}
