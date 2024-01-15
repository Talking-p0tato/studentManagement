package model;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class MemoryRepository {

    private Map<Integer, Student> studentMap = new HashMap<>();
    private List<Score> scoreList = new ArrayList<>();
    private Map<Integer, Subject> subjectMap= new HashMap();
    private int memberIdx = 1;

    private static final MemoryRepository instance = new MemoryRepository();

    public static MemoryRepository getInstance() {
        return instance;
    }

    private MemoryRepository() {
        //필수
        subjectMap.put(1, new Subject(1, "java", "필수"));
        subjectMap.put(2, new Subject(2, "객체지향", "필수"));
        subjectMap.put(3, new Subject(3, "spring", "필수"));
        subjectMap.put(4, new Subject(4, "jpa", "필수"));
        subjectMap.put(5, new Subject(5, "mysql", "필수"));
        //선택
        subjectMap.put(6, new Subject(6, "디자인패턴", "선택"));
        subjectMap.put(7, new Subject(7, "spring security", "선택"));
        subjectMap.put(8, new Subject(8, "redis", "선택"));
        subjectMap.put(9, new Subject(9, "mongodb", "선택"));
    }

    //학생 등록
    public Student addMember(String name, List<Subject> subjectList) {
        Student student = new Student(memberIdx, name, subjectList);
        studentMap.put(memberIdx++,student) ;
        return student;
    }

   public void addTestScore(Student student, Subject subject, int round, int score) {
        Score newscore = new Score(student, subject, round, score);
        newscore.setGrade();
        scoreList.add(newscore);
    }

    public Student findStudentById(int studentId) {
        return studentMap.getOrDefault(studentId, null);
    }

    public Subject findSubjectById(int subjectId) {
            return subjectMap.getOrDefault(subjectId, null);
    }

    public String findSubjectNameById(int subjectId) {
        return subjectMap.get(subjectId).getSubjectName();
    }

    public List<Subject> findAllSubject() {
        List<Subject> subjectList = new ArrayList<>();
        for (Map.Entry<Integer, Subject> subjectEntry : subjectMap.entrySet()) {
            subjectList.add(subjectEntry.getValue());
        }
        return subjectList;
    }

    //true : id를 가진 학생 있음, false : id를 가진 학생 없음

    public boolean isStudentExist(int studentId) {
        if(findStudentById(studentId) != null) {
            return true;
        }
        return false;
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
    //등록은 false일 때, 수정은 true일 때 만 가능
    public boolean isScoreRecordExist(int studentId, int subjectId, int round) {
        if(findScoreRecord(studentId, subjectId, round) != null) {
            return true;
        } else {
            return false;
        }
    }

    // 수강생의 과목별 시험 회차 및 점수를 업데이트하는 메소드
    public void updateTestScore(int studentId, int subjectId, int round, int score) {
        // 회차와 점수의 유효성 검사
        Score newScore = findScoreRecord(studentId, subjectId, round);
        newScore.setScore(score);
        newScore.setGrade();
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
    //

    //점수가 범위 안인지 유효성 검사
    public boolean validateAndParseScore(int inputScore) {
        if (isValidScore(inputScore)) {
            return true;
        } else return false;


    }

    private boolean isValidScore(int score) {
        return score >= 1 && score <= 100;

    }

    // true면 학생이 과목이 신청한 적이 있으니 진행
    // false면 잘못된 입력이니까 다시 시도
    //학생이 과목 신청한적 있는지 검증하는 메서드
    public boolean hasSubject(int studentId, int subjectId) {
        Student student = studentMap.get(studentId);
        for(Subject subject : student.getSubjectList()) {
            if (subject.getSubjectId() == subjectId) {
                return true;
            }
        }
        return false;
    //학생 등록
    public void addMember(String name, List<Subject> subjectList) {
        memberIdx++;
        Student student = new Student(memberIdx, name, subjectList);
        studentList.add(student);
    }
}
