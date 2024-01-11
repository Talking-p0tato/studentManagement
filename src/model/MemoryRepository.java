package model;

import java.util.List;

public class MemoryRepository {

    private List<Student> studentList;
    private List<Score> scoreList;
    private List<Subject> subjectList;
    private int memberidx = 0;

    private static final MemoryRepository instance = new MemoryRepository();

    public MemoryRepository getInstance() {
        return instance;
    }

    private MemoryRepository() {
    }

    //전체 수강생 목록 조회
    public List<Student> findAllStudent() {
        return studentList;
    }

    // 학생 등급 조회
    public List<Score> findGradebyIdAndName(int studentId, String subjectName) {
        List<Score> gradeList = this.scoreList;
        for (Score score : scoreList) {
            if (score.getStudent().getStudentId() == studentId && score.getSubject().getSubjectName().equals(subjectName)) {
                gradeList.add(score);
            }
        }
        return gradeList;
    }

    //학생 등록
    public void addMember(String name, List<Subject> subjectList) {
        memberidx++;
        Student student = new Student(memberidx, name, subjectList);
        studentList.add(student);
    }
}
