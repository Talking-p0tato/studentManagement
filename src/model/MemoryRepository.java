package model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryRepository {

    private List<Student> studentList = new ArrayList<>();
    private List<Score> scoreList = new ArrayList<>();
    private List<Subject> subjectList = new ArrayList<>();
    private int memberIdx = 0;

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
        //필수
        subjectList.add(new Subject(1, "java", "필수"));
        subjectList.add(new Subject(2, "객체지향", "필수"));
        subjectList.add(new Subject(3, "spring", "필수"));
        subjectList.add(new Subject(4, "jpa", "필수"));
        subjectList.add(new Subject(5, "mysql", "필수"));
        //선택
        subjectList.add(new Subject(6, "디자인패턴", "선택"));
        subjectList.add(new Subject(7, "spring security", "선택"));
        subjectList.add(new Subject(8, "redis", "선택"));
        subjectList.add(new Subject(9, "mongodb", "선택"));
    }

    //학생 등록
    public void addMember(String name, List<Subject> subjectList) {
        Student student = new Student(memberIdx++, name, subjectList);
        studentList.add(student);
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

    public Score findScoreRecord(int studentId, int subjectId, int round) {
        return scoreList.stream()
                .filter(score -> score.getStudent().getStudentId() == studentId)
                .filter(score -> score.getSubject().getSubjectId() == subjectId)
                .filter(score -> score.getRound() == round)
                .findFirst()
                .orElse(null);
    }

    //학생 선택과목 회차별 등급조회
    public List<Score> findAllScoreRecord(int studentId, int subjectId) {
        List<Score> studentAllScore = new ArrayList<>();
        for (Score score : scoreList) {
            if (score.getStudent().getStudentId() == studentId && score.getSubject().getSubjectId() == subjectId) {
                studentAllScore.add(score);
            }
        }
        return studentAllScore;
    }
    //true : 해당 과목에 차수에 대한 점수 존재, false : 해당 과목 차수에 대한 점수 없음


    public boolean isScoreRecordExist(int studentId, int subjectId, int round) {
        if (findScoreRecord(studentId, subjectId, round) != null) {

            return true;
        } else {
            return false;
        }
    }
    // 수강생의 과목별 시험 회차 및 점수를 업데이트하는 메소드

    public void updateTestScore(int studentId, int subjectName, int round, int score) {
        // 회차와 점수의 유효성 검사
        if (round < 1 || round > 10 || score < 0 || score > 100) {
            throw new IllegalArgumentException("잘못 된 점수 입니다.");
        }
        Score newScore = findScoreRecord(studentId, subjectName, round);
        newScore.setScore(score);
        newScore.setGrade();
    }


    // 특정 학생 특정 과목 모든 회차의 점수
    public List<Score> findAllScoresBySubject(int studentId, int subjectId) {
        return scoreList.stream()
                .filter(score -> score.getStudent().getStudentId() == studentId)
                .filter(score -> score.getSubject().getSubjectId() == subjectId)
                .collect(Collectors.toList());
    }

    //전체 수강생 목록 조회

    public List<Student> findAllStudent() {
        return studentList;
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
    //
    // true면 학생이 과목이 신청한 적이 있으니 진행
    // false면 잘못된 입력이니까 다시 시도

    // 학생이 수강한 과목이 맞는지 유효성 검사
    public boolean isValidSubject(int studentId, int subjectId) {
        Student student = getStudentList().get(studentId);

        List<Subject> subjectList1 = student.getSubjectList();

        return subjectList1.contains(subjectId);
    }

    //점수가 범위 안인지 유효성 검사
    public boolean validateAndParseScore(int inputScore) {
        if (isValidScore(inputScore)) {
            return true;
        } else return false;


    }

    private boolean isValidScore(int score) {
        return score >= 1 && score <= 100;

    }

    // 과목이름에 해당하는 과목객체를 반환하는 메서드
    public Subject getSubjectById(int subjectId) {
        for (Subject subject : getSubjectList()) {
            if (subject.getSubjectId() == subjectId) {
                return subject;
            }
        }
        return null;
    }

}
