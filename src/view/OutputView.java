package view;

import model.Score;
import model.Student;
import model.Subject;

import java.util.ArrayList;
import java.util.List;

public class OutputView {

    public static void printMainMenu() {
        System.out.println("|]=======[ Spring Track 수강생 관리 프로그램 ]=======[|");
        System.out.println("1. 수강생 등록");
        System.out.println("2. 수강생 목록 확인");
        System.out.println("3. 수강생 점수 관리");
        System.out.println();
    }

    public static void addMemberScreen() {
        System.out.println("|]=======[ Spring Track 수강생 등록 ]=======[|");
        System.out.print("등록하시려는 수강생 이름을 입력해주세요. : ");
    }

    public static void delayChangeScreen() {
        for (int i = 2; i > 0; i--) {
            System.out.println("(" + i + "초후 돌아갑니다.)");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public static void incorrectMenu() {
        System.out.println("잘못된 메뉴 선택입니다. 다시 입력해 주세요.");
    }

    public static void duplicateRound() {
        System.out.println("이미 점수가 등록된 회차 입니다. 다시 입력해 주세요.");
    }

    public static void completeSetScore() {
        System.out.println("점수 등록이 완료되었습니다.");
    }
    //3. 수강색 목록 확인 화면

    public static void showStudentList() {
        System.out.println("|]=======[ Spring Track 수강생 목록 조회 ]=======[|");
        System.out.println("1. 전체 수강생목록확인");
        System.out.println("0. 돌아가기");
    }

    public static void printAllStudent(List<Student> studentList) {
        if (studentList.isEmpty()) {
            System.out.println("조회 할 수 있는 수강생이 없습니다.");
        } else {
            System.out.println("등록된 수강생은 " + studentList.size() + "명 입니다.");
            for (Student student : studentList) {
                System.out.print("수강생ID : " + student.getStudentId() +
                                   "\n수강생이름 : " + student.getStudentName() + "\n");
                List<String> requiredSubjectNameList = new ArrayList<>();
                List<String> optionalSubjectNameList = new ArrayList<>();
                for(Subject subject : student.getSubjectList()) {
                    if(subject.getType().equals("필수")) {
                        requiredSubjectNameList.add(subject.getSubjectName());
                    }else if (subject.getType().equals("선택")){
                        optionalSubjectNameList.add(subject.getSubjectName());
                    }
                }
                System.out.println("신청한 필수 과목 : " + requiredSubjectNameList.toString());
                System.out.println("신청한 선택 과목 : " + optionalSubjectNameList.toString());
                System.out.println();
            }
            System.out.println("0. 뒤로가기");
        }
    }
    //3. 수강생 목록 확인 화면 (오류시)

    public static void failShowStudentList() {
        System.out.println("1과 0 중에서 다시 선택해주세요.");
    }
    //4. 수강생 점수관리 메인 화면 출력

    public static void showAddStudentIdScreen() {
        System.out.println("|]=======[ Spring Track 수강생 점수 관리 ]=======[|" + "\n");
        System.out.println("점수를 관리할 수강생 ID 를 입력해주세요.");
        System.out.print("->");
        //수강생 ID 인풋 받기 받은 후에 아래화면 출력
        //등록되지 않은 수강생의 ID 또는 다른 문자 입력시
        //System.out.println("잘못된 입력입니다. 다시입력해주세요.") 출력 후 -> 수강생 ID 입력화면
    }

    public static void showStudentScoreManageInfo(){
        System.out.println("|]=======[ Spring Track 수강생 점수 관리 ]=======[|"+"\n");
        System.out.println("1. (과목별) 시험 회차 및 점수 등록");
        System.out.println("2. (과목별) 시험 회차별 등급 조회 (수강생의 특정 과목 시험 회차별 등급을 조회할 수 있습니다.)");
        System.out.println("3. (과목별) 시험 회차 점수 수정");
        System.out.println("0. 메인 메뉴로 이동"+"\n");
        System.out.print("원하시는 작업 메뉴 번호를 입력해주세요 : ");
        //상세메뉴번호 입력 인풋
        // 잘못된 번호 또는 문자 입력시 System.out.println("잘못된 입력입니다. 다시입력해주세요.") -> 상세메뉴 입력화면
    }

    //4-1 학생이 수강한 과목 리스트 출력문
    //4-1 (과목별) 시험 회차 및 점수 등록 입력 받는 출력문

    public static void printAllSubjectOfStudent(List<Subject> subjectList) {
        for (Subject subject : subjectList) {
            System.out.printf("%d. %s\n",subject.getSubjectId(),subject.getSubjectName());
        }
        System.out.println("과목 번호를 입력해주세요.");
        System.out.print("->");
    }

    public static void showWrongAddContext(){
        System.out.println("잘못된 입력입니다. 다시입력해주세요.");
    }


    public static void showStudentSubjectList(String studentName, List<Subject> subjectList) {
        //수강 과목 선택 화면
        System.out.println("|]=======[ Spring Track 수강생 점수 수정 ]=======[|");
        System.out.println("수정을 원하는 과목을 선택해주세요.");
        System.out.println("수강생 이름: " + studentName);
        System.out.println("수강 과목");
        //수강생이 수강한 수강과목 출력 로직
        //ex)1. Java
        //2. 객체지향
        for (Subject subject : subjectList) {
            System.out.printf("%d. %s\n", subject.getSubjectId(), subject.getSubjectName());
        }
        System.out.println("0. 돌아가기");
        //과목 이름 인풋을 받음
        //수강생이 수강한 과목이름이 아니거나 잘못된 입력 값이라면
        //잘못된 입력입니다 다시입력해주세요. 출력 후 ->(회차 선택 화면)
        //0을 입력시 수강생 점수관리 메인화면으로 이동 -> (printStudentScoreManageScreen())
        //수강생의 수강 과목이 제대로 입력되면 아래문 출력

    }

    public static void showError(String message) {
        System.out.println(message);
    }


    public static void showUpdateStudentRoundGrade(String subjectName, List<Score> scoreList) {
        // 수정 회차 선택화면
        System.out.println("수정을 원하는 회차를 입력 해주세요.");
        System.out.println("과목명 : " + subjectName);
        for (int i = 0; i < scoreList.size(); i++) {
            Score studentScore = scoreList.get(i);
            System.out.printf("%d. %d회차 : %d" + "\n", i + 1, studentScore.getRound(), studentScore.getScore());
        }
        System.out.println("0. 돌아가기");
    }

    public static void promptForScoreUpdate() {
        System.out.println("점수를 수정해주세요.");
    }

    public static void showConfirmUpdateStudentScore() {
        System.out.println("수정되었습니다!");
    }
    // 수강생 등록 화면 . 1

    // 수강생 이름 입력 완료
    public static void addNameComplete() {
        System.out.println("수강생 이름이 입력되었습니다.");
    }

    public static void addSubjectComplete() {
        System.out.println("과목이 입력되었습니다.");
    }

    public static void wrongInputScreen() {
        System.out.println("잘못된 입력입니다.");
    }

    // 수강생 등록 완료 화면 . 2
    public static void requiredScreen() {
        System.out.print("|]=======[ Spring Track 필수과목 등록 ]=======[|");
        System.out.println("1. Java");
        System.out.println("2. 객체지향");
        System.out.println("3. Spring");
        System.out.println("4. JPA");
        System.out.println("5. MySQL");
        System.out.print("위 필수과목중 3가지를 골라 번호를 입력해주세요. : ");
    }

    public static void optionalScreen() {
        System.out.print("|]=======[ Spring Track 선택과목 등록 ]=======[|");
        System.out.println("1. 디자인패턴");
        System.out.println("2. Spring Security");
        System.out.println("3. Redis");
        System.out.println("4. MongoDB");
        System.out.print("위 필수과목중 2가지를 골라 번호를 입력해주세요. : ");
    }

    public static void wrongInputRequiredSubject() {
        System.out.println("3개 이상의 과목을 입력해주세요.");
    }

    public static void wrongInputOptionalSubject() {
        System.out.println("2개 이상의 과목을 입력해주세요.");
    }

    public static void completeAddStudentScreen() {
        System.out.println("수강생 등록이 완료되었습니다.");
    }

    public static void backToMainScreen() {
        System.out.println("메인 화면으로 돌아갑니다.");
    }

    // 잘못된 수강생 id입력시 예외처리 Output
    public static void wrongInputStudentId() {
        System.out.println("등록된 수강생이 없습니다. 다시 입력해주세요.");
    }

    //잘못 된 회차 입력시 예외처리 Output
    public static void wrongInputRound() {
        System.out.println("잘못된 회차 입력입니다. 회차는 1회차부터 10회차까지 있습니다.");
        System.out.println("다시 입력해주세요.");
    }

}


