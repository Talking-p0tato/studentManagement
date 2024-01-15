package controller;

import model.MemoryRepository;
import model.Score;
import model.Student;
import model.Subject;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;


public class StudentManagement {

    MemoryRepository repository = MemoryRepository.getInstance();

    public StudentManagement() {
    }

    public void start() {
        showMainMenu();
    }

    //메인메뉴화면 출력
    public void showMainMenu() {
        OutputView.printMainMenu();
        while (true) {
            int input = InputView.getUserIntInput();
            switch (input) {
                case 1: //수강생 등록 메서드
                case 2:
                    showSearchStudentMenu(); //수강생 조회 메뉴 출력
                case 3:
                    processStudentManage(); //수강생 점수관리 출력
                default:
                    OutputView.incorrectMenu(); //잘못된 입력시 오류메세지 출력 후 재입력받기.
            }
        }
    }

    //2. 수강생 조회 메뉴 화면
    public void showSearchStudentMenu() {
        OutputView.showStudentList();
        while (true) {
            int input = InputView.getUserIntInput();
            switch (input) {
                case 1:
                    showAllStudent(); //전체학생 조회 화면
                    break;
                case 0:
                    showMainMenu(); //메인 메뉴화면으로 돌아가기
                    break;
                default:
                    OutputView.failShowStudentList(); //잘못된 입력 처리
            }
        }
    }

    //2-1. 전체학생 조회 화면
    public void showAllStudent() {
        OutputView.printAllStudent(repository.findAllStudent());
        int input = InputView.getUserIntInput();
        while(true) {
            switch (input) {
                case 0 :
                    showMainMenu(); // 메인 메뉴로 돌아가기
                    break;
                default:
                    OutputView.wrongInputScreen(); //잘못된 입력
            }
        }
    }

    // 수강생 추가 메서드
    public void addStudent() {
        // 수강생 이름 입력
        OutputView.addMemberScreen();
        String name = InputView.getUserStringInput();
        OutputView.addNameComplete();

        // 수강생이 수강할 과목 입력
        List<Subject> subjectList = new ArrayList<>();
        chooseRequired(subjectList);
        chooseOptional(subjectList);

        // 데이터베이스에 저장
        Student savedStudent = repository.addMember(name, subjectList);

        // 수강생 등록 완료
        completeAddStudent(savedStudent, subjectList);
        backToMain();
    }

    // 추가 : 수강생 등록 완료
    private void completeAddStudent(Student student, List<Subject> subjectList) {
        OutputView.completeAddStudentScreen();
        System.out.println("수강생 번호 : " + student.getStudentId());
        System.out.println("수강생 이름 : " + student.getStudentName());
        System.out.println("필수 과목 : " + subjectList.subList(0, 3));
        System.out.println("선택 과목 : " + subjectList.subList(3, 5));
    }

    // 추가 : 필수과목
    private void chooseRequired(List<Subject> subjectList) {
        OutputView.requiredScreen();
        String requiredSubject = InputView.getUserStringInput();
        if (isValidSelectionR(requiredSubject)) {
            if (requiredSubject.split(",").length != 3) {
                OutputView.wrongInputRequiredSubject();
                OutputView.requiredScreen();
                requiredSubject = InputView.getUserStringInput();

            } else if (requiredSubject.split(",").length == 3) {
                String[] subjectArray = requiredSubject.split(",");
                int[] subjectArrayNum = new int[subjectArray.length];
                for (int i = 0; i < subjectArray.length; i++) {
                    subjectArrayNum[i] = Integer.parseInt(subjectArray[i]);
                    subjectList.add(repository.findAllSubject().get(subjectArrayNum[i]));
                    OutputView.addSubjectComplete();
                }
            } else {
                wrongInput();
            }
        } else {
            wrongInput();
            chooseRequired(subjectList);
        }
    }

    // 추가 : 선택과목
    private void chooseOptional(List<Subject> subjectList) {
        OutputView.optionalScreen();
        String optionalSubject = InputView.getUserStringInput();
        if (isValidSelectionO(optionalSubject)) {
            if (optionalSubject.split(",").length != 2) {
                OutputView.wrongInputOptionalSubject();
                OutputView.requiredScreen();

            } else if (optionalSubject.split(",").length == 2) {
                String[] subjectArray = optionalSubject.split(",");
                int[] subjectArrayNum = new int[subjectArray.length];
                for (int i = 0; i < subjectArray.length; i++) {
                    subjectArrayNum[i] = Integer.parseInt(subjectArray[i]);
                    subjectList.add(repository.findAllSubject().get(subjectArrayNum[i] + 5));
                    OutputView.addSubjectComplete();
                }
            } else {
                wrongInput();
            }
        } else {
            wrongInput();
            chooseOptional(subjectList);
        }
    }

    // 추가 : 잘못된 입력
    private void wrongInput() {
        OutputView.wrongInputScreen();
        addStudent();
    }

    // 추가 : 필수 Subject 검증
    private boolean isValidSelectionR(String input) {
        return input.matches("[1-5]+");
    }

    // 추가 :선택 Subject 검증
    private boolean isValidSelectionO(String input) {
        return input.matches("[1-4]+");
    }

    // 추가 : 메인으로 이동
    public void backToMain() {
        OutputView.backToMainScreen();
        try {
            Thread.sleep(3000);
            /* 메인으로 돌아가는 메서드 */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //3-0. 수강생 점수 관리 진입 시 studentId 입력
    public void processStudentManage() {
        OutputView.showAddStudentIdScreen();
        int studentId = InputView.getUserIntInputNoMessage();
        //메뉴에 진입 시 학생 id를 입력받고 검증하는 절차
        if (repository.isStudentExist(studentId)) {
            selectStudentManageInfo(studentId); //id 존재 시 다음 메뉴 선택화면으로 이동
        } else {
            System.out.println("해당 ID를 가진 학생이 없습니다. 다시 입력해주세요.");
            processStudentManage(); //해당 메서드 다시 호출
        }
    }


    //3. 수강생 점수 관리 메뉴 화면
    private void selectStudentManageInfo(int studentId) {
        OutputView.showStudentScoreManageInfo();
        int menuSelect = InputView.getUserIntInputNoMessage();
        switch (menuSelect) {
            case 0:
                showMainMenu(); //메인메뉴로 돌아가기
                break;
            case 1:
                showSetRoundScore(studentId); //3-1.(과목별) 시험 회차 및 점수 등록
                break;
            case 2:
                //과목별 회차 등급 조회 메서드
                break;
            case 3:
                showAndSelectSubject(repository.findStudentById(studentId)); //3-2(과목별) 시험 회차 점수 수정
                break;
            default:
                OutputView.showWrongAddContext();
                selectStudentManageInfo(studentId);
        }
    }

    //3-1.시험 회차 및 점수 동록
    public void showSetRoundScore(int studentId) {
        int inputSubjectId;
        int inputRound;
        OutputView.printAllSubjectOfStudent(repository.findStudentById(studentId).getSubjectList());
        while (true) {
            inputSubjectId = InputView.getUserIntInputNoMessage();
            if (repository.hasSubject(studentId, inputSubjectId)) {
                break;
            } else {
                //일단 넣어놓음 변경해야함
                OutputView.incorrectMenu();
            }
        }
        while (true) {
            inputRound = InputView.getRoundByUser();
            if(isValidRound(inputRound)) {
                if (!repository.isScoreRecordExist(studentId, inputSubjectId, inputRound)) {
                    //유저에게 점수 입력받기
                    int inputScore = InputView.getScoreByUser();
                    //점수 검증절차 넣어야함.
                    Student student = repository.findStudentById(studentId);
                    Subject subject = repository.findSubjectById(inputSubjectId);
                    repository.addTestScore(student, subject, inputRound, inputScore);
                    OutputView.completeSetScore();
                    OutputView.delayChangeScreen();
                    selectStudentManageInfo(studentId);
                    break;
                } else {
                    OutputView.duplicateRound();
                }
            } else {
                //수정해야함.
                OutputView.incorrectMenu();
            }
        }
    }
    //3-3. (과목별) 시험 회차 점수 수정 메뉴 진입
    public void showAndSelectSubject(Student student) {
        // 유효성 검사 메서드 필요함 hasSubject
        // 과목 목록 출력
        OutputView.showStudentSubjectList(student.getStudentName(), student.getSubjectList());

        while(true) {
            int subjectId = InputView.getUserIntInput();
            //0 입력 시 3.메뉴화면으로 이동
            if (subjectId == 0) {
                selectStudentManageInfo(student.getStudentId());
                break;
            }
            //입력받은 과목이 학생이 신청한 과목인지 검증
            else if(repository.hasSubject(student.getStudentId(), subjectId)) {
                //해당 과목에 등록된 회차별 점수출력 화면
                showRoundAndScore(subjectId, student.getStudentId());
                break;
            }
            //입력 받은 값이 0, 신청한 과목 번호가 모두 아닐 시
            else {
                OutputView.wrongInputScreen();
            }
        }
    }

    //3-3.(과목별) 시험 회차 점수 수정 메뉴 회차 입력 화면
    private void showRoundAndScore(int subjectId, int studentId) {
        // repository에서 해당 학생의 선택한 과목에 대한 점수 목록 보여주기
        List<Score> scores = repository.findAllScoreRecord(studentId, subjectId);

        // 회차 및 점수 출력
        OutputView.showUpdateStudentRoundGrade(repository.findSubjectNameById(subjectId), scores);

        // 회차 선택
        int roundNumber;
        do {
            System.out.println("수정할 회차 번호를 입력하세요 (1-10): ");
            roundNumber = InputView.getUserIntInput();
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
            newScore = InputView.getUserIntInput();
            if (!isValidScore(newScore)) {
                OutputView.showError("점수는 0에서 100 사이여야 합니다.");
                newScore = -1;
            }
        } while (newScore == -1);

        // 점수 업데이트
        scoreToUpdate.setScore(newScore);
        scoreToUpdate.setGrade();

        // 변경 사항을 저장소에 반영
        repository.updateTestScore(studentId, subjectId, roundNumber, newScore);

        // 업데이트 확인 메시지 출력
        OutputView.showConfirmUpdateStudentScore();

        // 점수 수정 후 점수 관리로 돌아감.
        OutputView.showStudentScoreManageInfo();
    }

    private boolean isValidRound(int round) {
        return round >= 1 && round <= 10; // 유효한 범위 안에 있을 때 true
    }

    private boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }
}