package model;

import java.util.ArrayList;
import java.util.List;

public class MemoryRepository {

    private List<Student> studentList;
    private List<Score> scoreList;
    private List<Subject> subjectList;

    private static final MemoryRepository instance = new MemoryRepository();

    public List<Student> getStudentList() {
        return studentList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public static MemoryRepository getInstance() {
        return instance;
    }

    private MemoryRepository() {
    }

    //전체 수강생 목록 조회
    public List<Student> findAllStudent() {
        return studentList;
    }
}
