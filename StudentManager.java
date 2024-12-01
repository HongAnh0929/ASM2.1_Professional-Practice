package ASM2;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentManager {
    private static ArrayList<Student> studentList = new ArrayList<Student>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("--- Student Manager ---");
            System.out.println("1. Enter student list");
            System.out.println("2. Find students by last name");
            System.out.println("3. Find and edit students by last name");
            System.out.println("4. End");
            System.out.print("Select function (1-4): ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addStudents();
                    break;
                case 2:
                    searchByLastName();
                    break;
                case 3:
                    searchAndEditStudent();
                    break;
                case 4:
                    System.out.println("End of program.");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        } while (choice != 4);
    }

    static class Student {
        private String firstName;
        private String lastName;
        private int age;

        // Constructor
        public Student(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        // Getter and setter methods
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        // toString method for displaying student info
        @Override
        public String toString() {
            return "Student{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    private static void addStudents() {
        System.out.print("Enter the number of students: ");
        int n = Integer.parseInt(scanner.nextLine()); // Dùng nextLine() và chuyển thành int

        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i + 1) + ":");

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Age: ");
            int age = 0;
            boolean validAge = false;
            while (!validAge) {
                try {
                    age = Integer.parseInt(scanner.nextLine());
                    validAge = true;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid age");
                }
            }

            // Thêm sinh viên vào danh sách
            studentList.add(new Student(firstName, lastName, age));
        }
        System.out.println("Add success" + n + " student.");
    }

    private static void searchByLastName() {
        System.out.print("Enter the last name you want to search for: ");
        String lastName = scanner.nextLine();
        boolean found = false;

        System.out.println("Search results");
        for (Student student : studentList) {
            if (student.getLastName().equalsIgnoreCase(lastName)) {
                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students were found with the last name " + lastName);
        }
    }

    private static void searchAndEditStudent() {
        System.out.print("Enter the first and last name to search: ");
        String fullName = scanner.nextLine();
        String[] nameParts = fullName.split(" ");
        if (nameParts.length < 2) {
            System.out.println("Please enter both first and last names.");
            return;
        }
        String lastName = nameParts[0];
        String firstName = nameParts[1];
        boolean found = false;

        for (Student student : studentList) {
            if (student.getLastName().equalsIgnoreCase(lastName) && student.getFirstName().equalsIgnoreCase(firstName)) {
                System.out.println("Current student information: " + student);
                System.out.print("Enter a new last name: ");
                student.setLastName(scanner.nextLine());
                System.out.print("Enter a new firstname: ");
                student.setFirstName(scanner.nextLine());
                System.out.print("Enter a new age: ");
                student.setAge(Integer.parseInt(scanner.nextLine()));
                System.out.println("Student information has been updated.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No students found with the first and last names are: \"" + fullName + "\".");
        }
    }
}
