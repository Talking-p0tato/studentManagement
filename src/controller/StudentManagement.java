package controller;
import model.*;
import view.OutputView;
import model.MemoryRepository;
import model.Student;


import java.util.Scanner;

import java.util.List;


public class StudentManagement {

    private MemoryRepository repository;
    private OutputView outputView;
    private Scanner scanner;

    private boolean isValidRound(int round) {
        return round >= 1 && round <= 10; // 유효한 범위 안에 있을 때 true
    }

    private boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }

    public StudentManagement(MemoryRepository repository, OutputView outputView) {
        this.repository = repository;
        this.outputView = outputView;
        this.scanner = new Scanner(System.in);
    }

    public void showAndSelectSubject(Student student) {
        // 유효성 검사 메서드 필요함 hasSubject
        // 과목 목록 출력
        outputView.showStudentSubjectList(student.getMemberName(), student.getSubjectList());

        int subjectNumber = scanner.nextInt();
        scanner.nextLine();

        if (subjectNumber == 0) {
            // 돌아가기 로직
            return;
        }

        Subject selectedSubject = student.getSubjectList().stream()
                .filter(subject -> subject.getSubjectId() == subjectNumber)
                .findFirst()
                .orElse(null);

        if (selectedSubject == null) {
            outputView.showError("선택한 과목이 목록에 없습니다.");
            return;
        }

        // 선택한 과목에 대한 회차 및 점수 출력
        showRoundAndScore(selectedSubject, student.getStudentId());
    }

    private void showRoundAndScore(Subject selectedSubject, int studentId) {
        // repository에서 해당 학생의 선택한 과목에 대한 점수 목록 보여주기
        List<Score> scores = repository.findAllScoresBySubject(studentId, selectedSubject.getSubjectId());

        // 회차 및 점수 출력
        outputView.showUpdateStudentRoundGrade(selectedSubject.getSubjectName(), scores);

        // 회차 선택
        int roundNumber;
        do {
            System.out.println("수정할 회차 번호를 입력하세요 (1-10): ");
            roundNumber = scanner.nextInt();
            scanner.nextLine();
            if (!isValidRound(roundNumber)) {
                outputView.showError("회차 번호는 1에서 10 사이여야 합니다.");
                roundNumber = -1;
            }
        } while (roundNumber == -1);

        // 점수 수정 안내 메시지 출력
        outputView.promptForScoreUpdate();

        // 회차에 해당하는 점수 객체 찾기
        int finalRoundNumber = roundNumber;
        Score scoreToUpdate = scores.stream()
                .filter(score -> score.getRound() == finalRoundNumber)
                .findFirst()
                .orElse(null);

        if (scoreToUpdate == null) {
            outputView.showError("선택한 회차가 목록에 없습니다.");
            return;
        }

        // 새로운 점수 입력
        int newScore;
        do {
            newScore = scanner.nextInt();
            scanner.nextLine();
            if (!isValidScore(newScore)) {
                outputView.showError("점수는 0에서 100 사이여야 합니다.");
                newScore = -1;
            }
        } while (newScore == -1);

        // 점수 업데이트
        scoreToUpdate.setScore(newScore);
        scoreToUpdate.setGrade();

        // 변경 사항을 저장소에 반영
        repository.updateTestScore(studentId, selectedSubject.getSubjectName(), roundNumber, newScore);

        // 업데이트 확인 메시지 출력
        outputView.showConfirmUpdateStudentScore();

        // 점수 수정 후 점수 관리로 돌아감.
        outputView.showStudentScoreManageInfo();

    }





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
                if (repository.getStudentList().isEmpty()) {
                    System.out.println("조회 할 수 있는 수강생이 없습니다.");
                } else {
                    System.out.println("등록된 수강생은 " + repository.getStudentList() + "명 입니다.");
                    for (Student value : repository.getStudentList()) {
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