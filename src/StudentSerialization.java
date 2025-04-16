import java.io.*;

class Student implements Serializable {
    int id;
    String name;
    double gpa;

    Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Student GPA: " + gpa);
    }
}

public class StudentSerialization {
    public static void main(String[] args) {
        Student s1 = new Student(101, "John Doe", 3.8);

        // Serialize
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.dat"))) {
            out.writeObject(s1);
            System.out.println("Student details saved successfully!");
        } catch (IOException e) {
            System.out.println("Serialization Error: " + e.getMessage());
        }

        // Deserialize
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.dat"))) {
            Student s = (Student) in.readObject();
            System.out.println("\nReading from file...");
            s.display();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization Error: " + e.getMessage());
        }
    }
}
