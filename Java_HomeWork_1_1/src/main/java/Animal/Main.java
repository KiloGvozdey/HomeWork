public class Main {
    public static void main(String[] args) {
        int countDog = 0;
        int countCat = 0;
        Animal[] animalList = new Animal[]{
                new Dog("Bobik"),
                new Cat("Barsik"),
                new Dog("Busya"),
                new Cat("Bombita"),
                new Cat("Chulita"),
                new Dog("PifPaf"),
                new Dog("Tuzik"),
                new Dog("Macros")
        };
        animalList[0].run(150);
        animalList[0].swim(5);
        animalList[0].swim(20);
        animalList[1].swim(5);

        for (int i = 0; i < animalList.length; i++) {
            if(animalList[i] instanceof Dog) countDog++;
            if(animalList[i] instanceof Cat) countCat++;
        }
        System.out.printf("Вы создали %s собак и %s котов.%n", countDog, countCat);
    }
}
