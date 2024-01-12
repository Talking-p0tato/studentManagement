package controller;
import model.*;

import model.MemoryRepository;
import model.Student;
import view.InputView;
import view.OutputView;

import java.util.Scanner;

import java.util.List;


public class StudentManagement {

    private MemoryRepository repository = MemoryRepository.getInstance();
    
    private boolean isValidRound(int round) {
        return round >= 1 && round <= 10; // 유효한 범위 안에 있을 때 true
    }

    private boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }
    

    public void showAndSelectSubject(Student student) {
        // 유효성 검사 메서드 필요함 hasSubject
        // 과목 목록 출력
        OutputView.showStudentSubjectList(student.getMemberName(), student.getSubjectList());

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
            OutputView.showError("선택한 과목이 목록에 없습니다.");
            return;
        }

        // 선택한 과목에 대한 회차 및 점수 출력
        showRoundAndScore(selectedSubject, student.getStudentId());
    }

    private void showRoundAndScore(Subject selectedSubject, int studentId) {
        // repository에서 해당 학생의 선택한 과목에 대한 점수 목록 보여주기
        List<Score> scores = repository.findAllScoresBySubject(studentId, selectedSubject.getSubjectId());

        // 회차 및 점수 출력
        OutputView.showUpdateStudentRoundGrade(selectedSubject.getSubjectName(), scores);

        // 회차 선택
        int roundNumber;
        do {
            System.out.println("수정할 회차 번호를 입력하세요 (1-10): ");
            roundNumber = scanner.nextInt();
            scanner.nextLine();
            if (!isValidRound(roundNumber)) {
                OutputView.showError("회차 번호는 1에서 10 사이여야 합니다.");
                roundNumber = -1;
            }
        } while (roundNumber == -1);

        // 점수 수정 안내 메시지 출력
        OutputView.promptForScoreUpdate();

        // 회차에 해당하는 점수 객체 찾기
        int finalRoundNumber = roundNumber;
        Score scoreToUpdate = scores.stream()
                .filter(score -> score.getRound() == finalRoundNumber)
                .findFirst()
                .orElse(null);

        if (scoreToUpdate == null) {
            OutputView.showError("선택한 회차가 목록에 없습니다.");
            return;
        }

        // 새로운 점수 입력
        int newScore;
        do {
            newScore = scanner.nextInt();
            scanner.nextLine();
            if (!isValidScore(newScore)) {
                OutputView.showError("점수는 0에서 100 사이여야 합니다.");
                newScore = -1;
            }
        } while (newScore == -1);

        // 점수 업데이트
        scoreToUpdate.setScore(newScore);
        scoreToUpdate.setGrade();

        // 변경 사항을 저장소에 반영
        repository.updateTestScore(studentId, selectedSubject.getSubjectName(), roundNumber, newScore);

        // 업데이트 확인 메시지 출력
        OutputView.showConfirmUpdateStudentScore();

        // 점수 수정 후 점수 관리로 돌아감.
        OutputView.showStudentScoreManageInfo();

    }
    
    InputView inputView = new InputView();
    Scanner scanner = new Scanner(System.in);

    public void processStudentManage() {
        OutputView.showAddStudentIdScreen();
        int studentId = scanner.nextInt();
        if (repository.isStudentExist(studentId)) {
            selectStudentManageInfo(studentId);
        }else {
            System.out.println("해당 ID를 가진 학생이 없습니다. 다시 입력해주세요.");
            processStudentManage();
        }
    }

    private void selectStudentManageInfo(int studentId) {
        OutputView.showStudentScoreManageInfo();
        int menuSelect = scanner.nextInt();
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
                OutputView.showWrongAddContext();
                selectStudentManageInfo(studentId);
        }
    }

    //4-1 페이지 작동 로직
    public void AddStudentScoreProcess(int studentId) {
        OutputView.showAddSubjectRoundScoreFrontScreen();
        //학생이 선택한 과목 출력
        AddSubjectNameProcess(studentId);
    }

    public void AddSubjectNameProcess(int studentId){
        OutputView.showAddSubjectNameScreen();
        int subjectId = scanner.nextInt();

        if (repository.isValidSubject(studentId, subjectId)){
            AddRondProcess(studentId,subjectId);
        }else {
            OutputView.showWrongAddContext();
            AddSubjectNameProcess(studentId);
        }

    }

    public void AddRondProcess(int studentId, int subjectId){
        OutputView.showAddRoundScreen();
        int round = scanner.nextInt();
        if (repository.isScoreRecordExist(studentId, subjectId, round)){
            AddScoreProcess(studentId, subjectId, round);
        }else {
            OutputView.showWrongAddContext();
            AddRondProcess(studentId, subjectId);
        }
    }

    public void AddScoreProcess(int studentId,int subjectId, int round){
        OutputView.showAddScoreScreen();
        int score = scanner.nextInt();
        if (repository.validateAndParseScore(score)) {
            repository.addTestScore(
                    repository.getStudentList().get(studentId),
                    repository.getSubjectById(subjectId),
                    round,
                    score);
            OutputView.showConfirmAddStudentScore();
            selectStudentManageInfo(studentId);
        } else {
            OutputView.showWrongAddContext();
                AddScoreProcess(studentId, subjectId, round);
        }
    }
    // ------- 4 - 1여기까지 작동로직-----

    public void showStudentInfo() {
        OutputView.showStudentList();
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        switch (input) {
            case 1:
                if (repository.getStudentList().isEmpty()) {
                    System.out.println("조회 할 수 있는 수강생이 없습니다.");
                } else {
                    System.out.println("등록된 수강생은 " + repository.getStudentList() + "명 입니다.");
                    for (Student value : repository.getStudentList()) {
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