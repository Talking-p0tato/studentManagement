package model;

import java.util.List;

public class Student {

    private int studentId;
    private String memberName;
    private List<Subject> subjectList;

    public Student(int studentId, String memberName, List<Subject> subjectList) {
        this.studentId = studentId;
        this.memberName = memberName;
        this.subjectList = subjectList;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getMemberName() {
        return memberName;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }


    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

}
