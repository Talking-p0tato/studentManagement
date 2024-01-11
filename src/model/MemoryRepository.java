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
    
    public void addTestScore(int studentId, String subjectName, int round, int score) {
        
    }


    public Optional<Student> findStudentById(int studentId) {
        return studentList.stream()
                .filter(student -> student.getStudentId() == studentId)
                .findFirst();
    }

    public boolean isStudentExists(int studentId) {
        return findStudentById(studentId).isEmpty();
    }

    public Optional<Score> findScoreRecord(int studentId, String subjectName) {
        return scoreList.stream()
                .filter(score -> score.getStudent().getStudentId() == studentId)
                .filter(score -> score.getSubject().getSubjectName().equals(subjectName))
                .findFirst();
    }
}
