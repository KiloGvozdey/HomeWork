public class Employee {
    private String name;
    private String position;
    private String eMail;
    private String phoneNumber;
    private int salary;
    private int age;


    public Employee(){

    }

    public Employee(String name, String position, String eMail, String phoneNumber, int salary, int age) {
        this.name = name;
        this.position = position;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    void print(){
        System.out.printf("My name is: %s, i am %s in our company, if you have any question for me, you may write to my e-mail box: %s or call on this number +%s.%n My current salary is %s, and my age is %s.%n"
                , name, position, eMail, phoneNumber, salary, age);
    }

    public int getAge() {
        return age;
    }
}
