package Task_3_4;

public class StringBuffer {
    private String string;

    public StringBuffer() {
        this.string = "A";
    }
    public synchronized void printA(){
        while (!string.equals("A")){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(string);
        string = "B";
        notifyAll();
    }
    public synchronized void printB(){
        while (!string.equals("B")){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(string);
        string = "C";
        notifyAll();
    }
    public synchronized void printC(){
        while (!string.equals("C")){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(string);
        string = "A";
        notifyAll();
    }


    public String getString() {
        return string;
    }
}
