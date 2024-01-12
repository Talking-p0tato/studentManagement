package model;

import java.util.List;

public class Student {

    private int studentId;
    private String studentName;
    private List<Subject> subjectList;

    public Student(int studentId, String studentName, List<Subject> subjectList) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjectList = subjectList;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }
}
