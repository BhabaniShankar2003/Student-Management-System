import java.io.*;
import java.util.*;

class Student {
    String roll, name, dept;
    int marks;

    Student(String roll, String name, String dept, int marks) {
        this.roll = roll;
        this.name = name;
        this.dept = dept;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return roll + "," + name + "," + dept + "," + marks;
    }
}

public class StudentManagement {
    static Scanner sc = new Scanner(System.in);
    static String fileName = "students.txt";

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addStudent() throws IOException {
        System.out.print("Enter Roll No: ");
        String roll = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();
        sc.nextLine();

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
        bw.write(new Student(roll, name, dept, marks).toString());
        bw.newLine();
        bw.close();

        System.out.println(" Student added successfully!");
    }

    static void viewStudents() throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("No records found!");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        System.out.println("\n--- Student List ---");
        while ((line = br.readLine()) != null)
            System.out.println(line);
        br.close();
    }

    static void searchStudent() throws IOException {
        System.out.print("Enter Roll No to search: ");
        String roll = sc.nextLine();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(roll + ",")) {
                System.out.println(" Found: " + line);
                found = true;
                break;
            }
        }
        if (!found) System.out.println(" Student not found!");
        br.close();
    }
}