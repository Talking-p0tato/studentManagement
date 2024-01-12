package controller;

import model.MemoryRepository;
import model.Student;
import view.OutputView;

import java.util.Scanner;


public class StudentManagement {
    public  int studentId;
    public  int menuSelect;

    public  String subjectName;

    public  int round;
    public int score;
    OutputView outputView= new OutputView();
    Scanner scanner = new Scanner(System.in);

    public void processStudentManage() {
        outputView.showAddStudentIdScreen();
        studentId = scanner.nextInt();
        if (MemoryRepository.getInstance().isStudentExist(studentId)) {
            selectStudentManageInfo(studentId);
        }else {
            System.out.println("해당 ID를 가진 학생이 없습니다. 다시 입력해주세요.");
            processStudentManage();
        }
    }

    private void selectStudentManageInfo(int studentId) {
        outputView.showStudentScoreManageInfo();
        menuSelect = scanner.nextInt();
        switch (menuSelect) {
            case 0:
                //메인메뉴로 진입하는 메서드
                break;
            case 1:
                AddStudentScoreProcess(studentId);
            break;
            case 2:
                //과목별 회차 등급 조회 메서드
                break;
            case 3:
                //과목별 회차 점수 수정 메서드
                break;
            default :
                outputView.showWrongAddContext();
                selectStudentManageInfo(studentId);
        }
    }

    //4-1 페이지 작동 로직
    public void AddStudentScoreProcess(int studentId) {
        outputView.showAddSubjectRoundScoreFrontScreen();
        //학생이 수강한 과목정보를 출력하는 메서드
        AddSubjectNameProcess(studentId);
    }

    public void AddSubjectNameProcess(int studentId){
        outputView.showAddSubjectNameScreen();
        subjectName = scanner.next();

        if (MemoryRepository.getInstance().isValidSubject(studentId, subjectName)){
            AddRondProcess(studentId,subjectName);
        }else {
            outputView.showWrongAddContext();
            AddSubjectNameProcess(studentId);
        }

    }

    public void AddRondProcess(int studentId, String subjectName){
        outputView.showAddRoundScreen();
        round = scanner.nextInt();
        if (MemoryRepository.getInstance().isScoreRecordExist(studentId, subjectName, round)){
            AddScoreProcess(studentId, subjectName, round);
        }else {
            outputView.showWrongAddContext();
            AddRondProcess(studentId, subjectName);
        }
    }

    public void AddScoreProcess(int studentId,String subjectName, int round){
        outputView.showAddScoreScreen();
        int score = scanner.nextInt();
        if (MemoryRepository.getInstance().validateAndParseScore(score)) {
            MemoryRepository.getInstance().addTestScore(
                    MemoryRepository.getInstance().getStudentList().get(studentId),
                    MemoryRepository.getInstance().getSubjectByName(subjectName),
                    round,
                    score);
            outputView.showConfirmAddStudentScore();
            selectStudentManageInfo(studentId);
        } else {
            outputView.showWrongAddContext();
                AddScoreProcess(studentId, subjectName, round);
        }
    }
    // ------- 4 - 1여기까지 작동로직-----

    public void showStudentInfo() {
        OutputView.showStudentList();
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        switch (input) {
            case 1:
                if (MemoryRepository.getInstance().getStudentList().isEmpty()) {
                    System.out.println("조회 할 수 있는 수강생이 없습니다.");
                } else {
                    System.out.println("등록된 수강생은 " + MemoryRepository.getInstance().getStudentList() + "명 입니다.");
                    for (Student value : MemoryRepository.getInstance().getStudentList()) {
                        System.out.println(" 수강생ID : " + value.getStudentId() + "\n수강생이름 : " + value.getMemberName() + "'\n수강신청한과목 : " + value.getSubjectList().toString());
                    }
                }
                break;
            case 0:
                //수강생 관리 프로그램 메인 화면으로 돌아가기
            default:
                OutputView.failShowStudentList();
                showStudentInfo(); //잘못 입력했을시 수강생 목록화면 다시 출력
        }
    }
}