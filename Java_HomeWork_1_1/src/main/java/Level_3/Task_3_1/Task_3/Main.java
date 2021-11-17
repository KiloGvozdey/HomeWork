package Level_3.Task_3_1.Task_3;


public class Main {
    public static void main(String[] args) {
       Box <Apple> appleBox1 = new Box<>();
       Box <Apple> appleBox2 = new Box<>();

       Box <Orange> orangeBox = new Box<>();

       orangeBox.addToBox(new Orange());
       orangeBox.addToBox(new Orange());


       appleBox1.addToBox(new Apple());
       appleBox1.addToBox(new Apple());
       appleBox1.addToBox(new Apple());


       appleBox2.addToBox(new Apple());


       appleBox1.migrateFruit(appleBox2);

        System.out.println(appleBox1.compare(appleBox2));
        System.out.println(appleBox1.compare(orangeBox));

        System.out.println(appleBox1.getWeight());
        System.out.println(appleBox2.getWeight());
        System.out.println(orangeBox.getWeight());



    }
}
