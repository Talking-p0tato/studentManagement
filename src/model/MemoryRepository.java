package model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryRepository {

    private List<Student> studentList;
    private List<Score> scoreList;
    private List<Subject> subjectList;
    private int memberIdx = 0;

    private static final MemoryRepository instance = new MemoryRepository();

    public MemoryRepository getInstance() {
        return instance;
    }

    private MemoryRepository() {
    }

    public Map<String, List<Score>> getScoresByStudent(int studentId) {
        // 해당 학생 찾기
        Optional<Student> studentOpt = studentList.stream()
                .filter(student -> student.getStudentId() == studentId)
                .findFirst();

        if (!studentOpt.isPresent()) {
            throw new IllegalArgumentException("해당 학생을 찾을 수 없습니다.");
        }

        // 학생이 수강한 모든 과목의 점수를 맵으로 반환
        Map<String, List<Score>> scoresBySubject = new HashMap<>();
        Student student = studentOpt.get();
        for (Subject subject : student.getSubjectList()) {
            List<Score> scoresForSubject = scoreList.stream()
                    .filter(score -> score.getStudent().equals(student) && score.getSubject().equals(subject))
                    .collect(Collectors.toList());
            scoresBySubject.put(subject.getSubjectName(), scoresForSubject);
        }

        return scoresBySubject;
    }

    // 수강생의 과목별 시험 회차 및 점수를 업데이트하는 메소드
    public void updateTestScore(int studentId, String subjectName, int round, int score) {
        // 회차와 점수의 유효성 검사
        if (round < 1 || round > 10 || score < 0 || score > 100) {
            throw new IllegalArgumentException("잘못 된 점수 입니다.");
        }

        // 해당 학생 찾기
        Optional<Student> studentOpt = studentList.stream()
                .filter(student -> student.getStudentId() == studentId)
                .findFirst();

        if (!studentOpt.isPresent()) {
            throw new IllegalArgumentException("해당 학생을 찾을 수 없습니다.");
        }

        // 해당 과목 찾기
        Optional<Subject> subjectOpt = subjectList.stream()
                .filter(subject -> subject.getSubjectName().equals(subjectName))
                .findFirst();

        if (!subjectOpt.isPresent()) {
            throw new IllegalArgumentException("과목을 찾을 수 없습니다.");
        }

        // 과목의 해당 회차 점수가 이미 있는지 확인
        Optional<Score> existingScore = scoreList.stream()
                .filter(scoreObj -> scoreObj.getStudent().getStudentId() == studentId
                        && scoreObj.getSubject().getSubjectName().equals(subjectName)
                        && scoreObj.getRound() == round)
                .findFirst();

        if (existingScore.isPresent()) {
            // 점수가 이미 등록되어 있다면 업데이트
            existingScore.get().setScore(score);
            // 등급도 업데이트
            existingScore.get().setGrade(calculateGrade(score, subjectOpt.get().getType()));
        } else {
            // 새로운 점수 객체 생성 및 리스트에 추가
            Score newScore = new Score(studentOpt.get(), subjectOpt.get(), round, score);
            // 등급 계산 및 설정
            newScore.setGrade(calculateGrade(score, subjectOpt.get().getType()));
            scoreList.add(newScore);
        }
    }

    // 점수와 선택 과목 여부에 따라 등급을 계산하는 메서드
    public String calculateGrade(int score, String type) {
        if (type.equals("선택")) {
            if (score >= 90) {
                return "A";
            } else if (score >= 80) {
                return "B";
            } else if (score >= 70) {
                return "C";
            } else if (score >= 60) {
                return "D";
            } else if (score >= 50) {
                return "F";
            } else {
                return "N";
            }
        } else {
            if (score >= 95) {
                return "A";
            } else if (score >= 90) {
                return "B";
            } else if (score >= 80) {
                return "C";
            } else if (score >= 70) {
                return "D";
            } else if (score >= 60) {
                return "F";
            } else {
                return "N";
            }
        }
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

    //학생 등록
    public void addMember(String name, List<Subject> subjectList) {
        memberIdx++;
        Student student = new Student(memberIdx, name, subjectList);
        studentList.add(student);
    }
}

