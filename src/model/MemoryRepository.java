package model;

import java.util.List;

public class MemoryRepository {

    private List<Student> studentList;
    private List<Score> scoreList;
    private List<Subject> subjectList;

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
    }

    // 전체 수강생 목록 확인
    public void findAllStudent() {
        if (studentList.isEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
        } else
            for (Student student : studentList) {
                System.out.printf("학생코드 : %d, 이름 : %s", student.getStudentId(), student.getMemberName());
                List<Subject> subjects = student.getSubjectList();
                if (!subjects.isEmpty()) {
                    for (Subject subject : subjects) {
                        System.out.printf(", 과목코드 : %s, 과목이름 : %s, 과목타입 : %s", subject.getSubjectId(), subject.getSubjectName(), subject.getType());
                    }
                }
                System.out.println();
            }
    }
}
