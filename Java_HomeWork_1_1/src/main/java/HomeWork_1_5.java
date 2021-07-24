public class HomeWork_1_5 {
    public static void main(String[] args) {
        Employee[] employeesArray = new Employee[5];
        employeesArray[0] = new Employee("Gordienko Alexander", "Lab-Technitian", "agordienko@mail.ru", "79869994455", 90000, 27);
        employeesArray[1] = new Employee("Skvortsov Sergey", "Lab-Technitian", "sskvortsov@mail.ru", "79825556644", 100000, 36);
        employeesArray[2] = new Employee("Karpov Ilya", "Lab-Technitian", "ikarpov@mail.ru", "79854488455", 85000, 27);
        employeesArray[3] = new Employee("Terzy Ivan", "Engineer", "iterzy@mail.ru", "79823336685", 115000, 45);
        employeesArray[4] = new Employee("Tuev Anatoliy", "Equipment operator", "atuev@mail.ru", "79831477756", 75000, 47);

        printEmployeeMore40Year(employeesArray);

    }
    static void printEmployeeMore40Year(Employee[] employees){
        for (Employee employee: employees) {
            if(employee.getAge() > 40) employee.print();
        }
    }
}
