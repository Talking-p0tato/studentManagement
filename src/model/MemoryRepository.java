package model;

import java.util.ArrayList;
import java.util.List;

public class MemoryRepository {

    private List<Student> studentList;
    private List<Score> scoreList;
    private List<Subject> subjectList;
    private int memberIdx = 0;

    private static final MemoryRepository instance = new MemoryRepository();

    public static MemoryRepository getInstance() {
        return instance;
    }

    private MemoryRepository() {
    }


    // 학생 등급 조회
    public List<Score> findGradeByIdAndName(int studentId, String subjectName) {
        List<Score> gradeList = this.scoreList;
        for (Score score : scoreList) {
            if (score.getStudent().getStudentId() == studentId && score.getSubject().getSubjectName().equals(subjectName)) {
                gradeList.add(score);
            }
        }
        return gradeList;
    }

    //전체 수강생 목록 조회
    public List<Student> findAllStudent() {
        return studentList;
    }



    //학생 등록
    public void addMember(String name, List<Subject> subjectList) {
        memberIdx++;
        Student student = new Student(memberIdx, name, subjectList);
        studentList.add(student) ;
    }
}

