package model;

import java.util.List;
import java.util.Optional;

public class MemoryRepository {

    private List<Student> studentList;
    private List<Score> scoreList;
    private List<Subject> subjectList;

    private static final MemoryRepository instance = new MemoryRepository();

    public MemoryRepository getInstance() {
        return instance;
    }

    private MemoryRepository() {
    }

    public void addTestScore(Student student, Subject subject, int round, int score) {
        Score newscore = new Score(student, subject, round, score);
        newscore.setGrade();
        scoreList.add(newscore);
    }

    public Optional<Student> findStudentById(int studentId) {
        return studentList.stream()
                .filter(student -> student.getStudentId() == studentId)
                .findFirst();
    }

    //true : id를 가진 학생 있음, false : id를 가진 학생 없음
    public boolean isStudentExist(int studentId) {
        return !findStudentById(studentId).isEmpty();
    }

    public Optional<Score> findScoreRecord(int studentId, String subjectName, int round) {
        return scoreList.stream()
                .filter(score -> score.getStudent().getStudentId() == studentId)
                .filter(score -> score.getSubject().getSubjectName().equals(subjectName))
                .filter(score -> score.getRound() == round)
                .findFirst();
    }

    //true : 해당 과목에 차수에 대한 점수 존재, false : 해당 과목 차수에 대한 점수 없음
    public boolean isScoreRecordExist(int studentId, String subjectName, int round) {
        return !findScoreRecord(studentId, subjectName, round).isEmpty();
    }
}
