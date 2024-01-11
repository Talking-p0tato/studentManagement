package model;

import java.util.List;

public class MemoryRepository {

    private List<Student> studentList;
    private List<Score> scores;
    private List<Subject> subjectList;
    private int memberidx = 0;

    private static final MemoryRepository instance = new MemoryRepository();

    public MemoryRepository getInstance() {
        return instance;
    }

    private MemoryRepository() {
    }


    //학생 등록
    public void addMember(String name, List<Subject> subjectList) {
        memberidx++;
        Student student = new Student(memberidx, name, subjectList);
        studentList.add(student) ;
    }
}