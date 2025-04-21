package Core_3;

import java.time.LocalDate;
import java.util.Arrays;

// Базовый класс Сотрудник
class Employee {
    private String name;
    private double salary;
    private LocalDate hireDate;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDate = LocalDate.of(year, month, day);
    }

    // Метод-компаратор для сравнения дат без условных операторов
    public int compareDates(int year1, int month1, int day1, int year2, int month2, int day2) {
        LocalDate date1 = LocalDate.of(year1, month1, day1);
        LocalDate date2 = LocalDate.of(year2, month2, day2);
        return date1.compareTo(date2);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                '}';
    }
}

// Класс Руководитель (наследник Сотрудника)
class Manager extends Employee {
    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
    }

    // Статический метод для повышения зарплаты всем, кроме руководителей
    public static void raiseSalaries(Employee[] employees, double percent) {
        for (Employee emp : employees) {
            if (!(emp instanceof Manager)) {
                emp.setSalary(emp.getSalary() * (1 + percent / 100));
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем массив сотрудников
        Employee[] employees = {
                new Employee("Иван Иванов", 50000, 2020, 5, 15),
                new Employee("Петр Петров", 60000, 2019, 3, 10),
                new Manager("Сидор Сидоров", 80000, 2018, 1, 20),
                new Employee("Анна Аннова", 55000, 2021, 7, 5)
        };

        System.out.println("До повышения:");
        Arrays.stream(employees).forEach(System.out::println);

        // Повышаем зарплату на 10% всем, кроме руководителей
        Manager.raiseSalaries(employees, 10);

        System.out.println("\nПосле повышения:");
        Arrays.stream(employees).forEach(System.out::println);

        // Демонстрация работы компаратора дат
        Employee emp = employees[0];
        int result = emp.compareDates(2020, 5, 15, 2021, 7, 5);
        System.out.println("\nСравнение дат: " + result); // Отрицательное число, так как первая дата раньше
    }
}