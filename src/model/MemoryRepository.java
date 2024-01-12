package model;

import java.util.*;


public class MemoryRepository {

    private Map<Integer, Student> studentMap = new HashMap<>();
    private List<Score> scoreList = new ArrayList<>();
    private List<Subject> subjectList = new ArrayList<>();
    private int memberIdx = 1;

    private static final MemoryRepository instance = new MemoryRepository();

    public static MemoryRepository getInstance() {
        return instance;
    }

    private MemoryRepository() {
        //필수
        subjectList.add(new Subject(1,"java","필수"));
        subjectList.add(new Subject(2,"객체지향","필수"));
        subjectList.add(new Subject(3,"spring","필수"));
        subjectList.add(new Subject(4,"jpa","필수"));
        subjectList.add(new Subject(5,"mysql","필수"));
        //선택
        subjectList.add(new Subject(5,"디자인패턴","선택"));
        subjectList.add(new Subject(5,"spring security","선택"));
        subjectList.add(new Subject(5,"redis","선택"));
        subjectList.add(new Subject(5,"mongodb","선택"));
    }

    //학생 등록
    public void addMember(String name, List<Subject> subjectList) {
        Student student = new Student(memberIdx, name, subjectList);
        studentMap.put(memberIdx++,student) ;
    }

   public void addTestScore(Student student, Subject subject, int round, int score) {
        Score newscore = new Score(student, subject, round, score);
        newscore.setGrade();
        scoreList.add(newscore);
    }

    public Student findStudentById(int studentId) {
        if(studentMap.containsKey(studentId)) {
            return studentMap.get(studentId);
        } else {
            return null;
        }
    }

    //true : id를 가진 학생 있음, false : id를 가진 학생 없음
    public boolean isStudentExist(int studentId) {
        if(findStudentById(studentId) != null) {
            return true;
        }
        return false;
    }

    public Score findScoreRecord(int studentId, String subjectName, int round) {
        return scoreList.stream()
                .filter(score -> score.getStudent().getStudentId() == studentId)
                .filter(score -> score.getSubject().getSubjectName().equals(subjectName))
                .filter(score -> score.getRound() == round)
                .findFirst()
                .orElse(null);
    }

    //true : 해당 과목에 차수에 대한 점수 존재, false : 해당 과목 차수에 대한 점수 없음
    //등록은 false일 때, 수정은 true일 때 만 가능
    public boolean isScoreRecordExist(int studentId, String subjectName, int round) {
        if(findScoreRecord(studentId, subjectName, round) != null) {
            return true;
        } else {
            return false;
        }
    }

    // 수강생의 과목별 시험 회차 및 점수를 업데이트하는 메소드
    public void updateTestScore(int studentId, String subjectName, int round, int score) {
        // 회차와 점수의 유효성 검사
        vaildateRoundScore(round, score);
        Score newScore = findScoreRecord(studentId, subjectName, round);
        newScore.setScore(score);
        newScore.setGrade();
    }

    private void vaildateRoundScore(int round, int score) {
        if (round < 1 || round > 10 || score < 0 || score > 100) {
            throw new IllegalArgumentException("잘못 된 점수 입니다.");
        }
    }

    //전체 수강생 목록 조회
    public List<Student> findAllStudent() {
        List<Student> studentList = new ArrayList<>();
        for (Map.Entry<Integer, Student> student : studentMap.entrySet()) {
            studentList.add(student.getValue());
        }
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

    //학생이 과목 신청한적 있는지 검증하는 메서드
    public boolean hasSubject(int studentId, int subjectId) {
        Student student = studentMap.get(studentId);
        for(Subject subject : student.getSubjectList()) {
            if (subject.getSubjectId() == subjectId) {
                return true;
            }
        }
        return false;
    }
}

