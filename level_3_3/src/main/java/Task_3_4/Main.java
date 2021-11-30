package Task_3_4;

public class Main {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
       new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                stringBuffer.printA();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                stringBuffer.printB();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                stringBuffer.printC();
            }
        }).start();
    }
}
