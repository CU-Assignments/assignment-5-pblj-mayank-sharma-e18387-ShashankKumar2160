import java.io.*;
import java.util.*;

class Employee implements Serializable {
    int id;
    String name;
    String designation;
    double salary;

    Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    void display() {
        System.out.println("Employee ID: " + id + ", Name: " + name +
                ", Designation: " + designation + ", Salary: " + salary);
    }
}

public class EmployeeManager {
    static final String FILE_NAME = "employees.dat";

    public static void addEmployee() {
        try (Scanner sc = new Scanner(System.in);
             ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME, true)) {
                 protected void writeStreamHeader() {}  // Prevent header corruption when appending
             }) {
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine();  // consume newline
            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Designation: ");
            String desig = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            Employee emp = new Employee(id, name, desig, salary);
            out.writeObject(emp);

            System.out.println("Employee added successfully!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void displayEmployees() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("Employee Records:");
            while (true) {
                Employee e = (Employee) in.readObject();
                e.display();
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (FileNotFoundException e) {
            System.out.println("No employee data found.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading employees: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nMenu:\n1. Add an Employee\n2. Display All Employees\n3. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: displayEmployees(); break;
                case 3: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }
}
