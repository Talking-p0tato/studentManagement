import controller.StudentManagement;
import model.MemoryRepository;
import model.OutputView;

public class Main {
    public static void main(String[] args) {
        StudentManagement studentManagement = new StudentManagement();
        studentManagement.start();
    }
}