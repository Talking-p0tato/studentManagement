package model;

import java.util.List;

public class Student {

    private int studentId;
    private static String memberName;
    private static List<Subject> subjectList;

    public Student(int studentId, String memberName, List<Subject> subjectList) {
        this.studentId = studentId;
        Student.memberName = memberName;
        Student.subjectList = subjectList;
    }

    public int getStudentId() {
        return studentId;
    }

    public static String getMemberName() {
        return memberName;
    }

    public static List<Subject> getSubjectList() {
        return subjectList;
    }


    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setMemberName(String memberName) {
        Student.memberName = memberName;
    }

    public void setSubjectList(List<Subject> subjectList) {
        Student.subjectList = subjectList;
    }

}
