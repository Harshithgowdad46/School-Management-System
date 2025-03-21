package school;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Student class
class Student {
    private int id;
    private String name;
    private int grade;
    private int feesPaid;
    private int totalFees;

    public Student(int id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.feesPaid = 0;
        this.totalFees = 150000; // Assume total fees per student
    }

    public void payFees(int amount) {
        if (amount > 0) {
            feesPaid += amount;
            School.updateTotalMoneyEarned(amount);
        } else {
            System.out.println("Invalid fee amount.");
        }
    }

    public int getRemainingFees() {
        return totalFees - feesPaid;
    }

    @Override
    public String toString() {
        return "Student: " + name + ", Grade: " + grade + ", Fees Paid: " + feesPaid + ", Remaining Fees: " + getRemainingFees();
    }
}

// Teacher class
class Teacher {
    private int id;
    private String name;
    private int salary;

    public Teacher(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void receiveSalary(int salary) {
        if (salary > 0) {
            School.updateTotalMoneySpent(salary);
        } else {
            System.out.println("Invalid salary.");
        }
    }

    @Override
    public String toString() {
        return "Teacher: " + name + ", Salary: " + salary;
    }
}

// School class
class School {
    private static int totalMoneyEarned;
    private static int totalMoneySpent;
    private List<Teacher> teachers;
    private List<Student> students;

    public School() {
        teachers = new ArrayList<>();
        students = new ArrayList<>();
        totalMoneyEarned = 0;
        totalMoneySpent = 0;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public static void updateTotalMoneyEarned(int amount) {
        totalMoneyEarned += amount;
    }

    public static void updateTotalMoneySpent(int amount) {
        totalMoneySpent += amount;
    }

    public int getNetBalance() {
        return totalMoneyEarned - totalMoneySpent;
    }

    @Override
    public String toString() {
        return "Total Money Earned: " + totalMoneyEarned + ", Total Money Spent: " + totalMoneySpent + ", Net Balance: " + getNetBalance();
    }

    public void displayTeachers() {
        System.out.println("\nTeachers:");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }

    public void displayStudents() {
        System.out.println("\nStudents:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
}

public class SchoolManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        School school = new School();

        // Add teachers
        school.addTeacher(new Teacher(1, "Mr. Abdul", 50000));
        school.addTeacher(new Teacher(2, "Ms. Salim", 55000));

        // Add students
        school.addStudent(new Student(1, "Shiek", 10));
        school.addStudent(new Student(2, "Mohmmad", 12));

        // Student pays fees
        school.getStudents().get(0).payFees(100000);
        school.getStudents().get(1).payFees(140000);

        // Teachers receive salary
        school.getTeachers().get(0).receiveSalary(50000);
        school.getTeachers().get(1).receiveSalary(55000);

        school.displayStudents();
        school.displayTeachers();
        System.out.println("\nSchool Financials:");
        System.out.println(school);

        scanner.close();
    }
}