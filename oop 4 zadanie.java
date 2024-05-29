import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Класс Учитель
class Teacher {
    private String name;
    private String subject;

    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}

// Класс УчительСервис
class TeacherService {
    private List<Teacher> teachers;

    public TeacherService() {
        this.teachers = new ArrayList<>();
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void editTeacher(int index, String name, String subject) {
        if (index >= 0 && index < teachers.size()) {
            Teacher teacher = teachers.get(index);
            teacher.setName(name);
            teacher.setSubject(subject);
        } else {
            System.out.println("Teacher not found.");
        }
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
}

// Класс УчительВью
class TeacherView {
    private Scanner scanner;

    public TeacherView() {
        this.scanner = new Scanner(System.in);
    }

    public Teacher createTeacher() {
        System.out.print("Enter teacher name: ");
        String name = scanner.nextLine();
        System.out.print("Enter teacher subject: ");
        String subject = scanner.nextLine();
        return new Teacher(name, subject);
    }

    public int getTeacherIndex() {
        System.out.print("Enter teacher index: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayTeachers(List<Teacher> teachers) {
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println(i + ": " + teachers.get(i));
        }
    }

    public void editTeacher(Teacher teacher) {
        System.out.print("Enter new teacher name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new teacher subject: ");
        String subject = scanner.nextLine();
        teacher.setName(name);
        teacher.setSubject(subject);
    }
}

// Класс УчительКонтроллер
class TeacherController {
    private TeacherService teacherService;
    private TeacherView teacherView;

    public TeacherController() {
        this.teacherService = new TeacherService();
        this.teacherView = new TeacherView();
    }

    public void addTeacher() {
        Teacher teacher = teacherView.createTeacher();
        teacherService.addTeacher(teacher);
    }

    public void editTeacher() {
        teacherView.displayTeachers(teacherService.getTeachers());
        int index = teacherView.getTeacherIndex();
        Teacher teacher = teacherService.getTeachers().get(index);
        teacherView.editTeacher(teacher);
    }

    public void displayTeachers() {
        teacherView.displayTeachers(teacherService.getTeachers());
    }
}

// Main class for testing
public class Main {
    public static void main(String[] args) {
        TeacherController teacherController = new TeacherController();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Add Teacher");
            System.out.println("2. Edit Teacher");
            System.out.println("3. Display Teachers");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    teacherController.addTeacher();
                    break;
                case 2:
                    teacherController.editTeacher();
                    break;
                case 3:
                    teacherController.displayTeachers();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
