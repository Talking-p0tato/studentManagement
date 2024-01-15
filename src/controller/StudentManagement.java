package controller;

import model.MemoryRepository;
import model.Score;
import model.Student;
import model.Subject;
import view.InputView;
import view.OutputView;

import java.util.*;


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
                case 1:
                    addStudent();
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
                case 2:
                    showAllStudentByName(); //수강생이름으로 조회
                case 3:
                    showAllStudentByID(); //수강생고유번호로 조회
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
        while (true) {
            int input = InputView.getUserIntInput();
            switch (input) {
                case 0:
                    showMainMenu(); // 메인 메뉴로 돌아가기
                    break;
                case 1:
                    showSearchStudentMenu(); //수강생 조회 메뉴로 돌아가기
                    break;
                default:
                    OutputView.wrongInputScreen(); //잘못된 입력
            }
        }
    }

    //2-2. 수강생이름으로 조회 화면
    public void showAllStudentByName() {
        while (true) {
            OutputView.enterName();
            String name = InputView.getUserStringInput();
            OutputView.printAllStudentByName(repository.findAllStudent(), name);
            int input = InputView.getUserIntInput();
            switch (input) {
                case 0:
                    showMainMenu(); // 메인 메뉴로 돌아가기
                    break;
                case 1:
                    showSearchStudentMenu(); //수강생 조회 메뉴로 돌아가기
                    break;
                default:
                    OutputView.wrongInputScreen(); //잘못된 입력
            }
        }
    }

    //2-3. 수강생ID로 조회 화면
    public void showAllStudentByID() {
        while (true) {
            OutputView.enterID();
            int ID = InputView.getUserIntInput();
            OutputView.printAllStudentByID(repository.findAllStudent(), ID);
            int input = InputView.getUserIntInput();
            switch (input) {
                case 0:
                    showMainMenu(); // 메인 메뉴로 돌아가기
                    break;
                case 1:
                    showSearchStudentMenu(); //수강생 조회 메뉴로 돌아가기
                    break;
                default:
                    OutputView.wrongInputScreen(); //잘못된 입력
            }
        }
    }

    // 수강생 추가 메서드
    public void addStudent() {
        // 수강생 이름 입력
        System.out.println();
        OutputView.addMemberScreen();
        String name = InputView.getUserStringInputNoMessageNext();
        OutputView.addNameComplete();
        System.out.println();

        // 수강생이 수강할 과목 입력
        List<Subject> subjectList = new ArrayList<>();
        chooseRequired(subjectList);
        System.out.println();
        chooseOptional(subjectList);
        System.out.println();

        // 데이터베이스에 저장
        Student savedStudent = repository.addMember(name, subjectList);

        // 수강생 등록 완료
        completeAddStudent(savedStudent, subjectList);
        System.out.println();
        backToMain();
    }

    // 추가 : 수강생 등록 완료
    private void completeAddStudent(Student student, List<Subject> subjectList) {
        OutputView.completeAddStudentScreen();
        System.out.println("수강생 번호 : " + student.getStudentId());
        System.out.println("수강생 이름 : " + student.getStudentName());
        System.out.println("필수 과목 : " + subjectList.get(0).getSubjectName() + ", " + subjectList.get(1).getSubjectName() + ", " + subjectList.get(2).getSubjectName());
        System.out.println("선택 과목 : " + subjectList.get(3).getSubjectName() + ", " + subjectList.get(4).getSubjectName());
    }

    // 추가 : 필수과목
    private void chooseRequired(List<Subject> subjectList) {
        OutputView.requiredScreen();
        Set<Integer> selectedSubjects = new HashSet<>();
        String requiredSubject = InputView.getUserStringInputNoMessageNextline().replaceAll(" ", "");

        if (isValidSelectionR(requiredSubject)) {
            selectedSubjects.clear();
            if (requiredSubject.split(",").length != 3) {
                OutputView.wrongInputRequiredSubject();
                chooseRequired(subjectList);

            } else if (requiredSubject.split(",").length == 3) {
                String[] subjectArray = requiredSubject.split(",");
                int[] subjectArrayNum = new int[subjectArray.length];

                for (int i = 0; i < subjectArray.length; i++) {
                    subjectArrayNum[i] = Integer.parseInt(subjectArray[i]);
                    selectedSubjects.add(subjectArrayNum[i]);
                }
                if (selectedSubjects.size() != subjectArrayNum.length) {
                    OutputView.wrongInputSubject();
                    System.out.println();
                    chooseRequired(subjectList);
                } else {
                    for (int i = 0; i < subjectArray.length; i++) {
                        subjectList.add(repository.findAllSubject().get(subjectArrayNum[i] - 1));
                    }
                }
            } else {
                wrongInput();
                chooseRequired(subjectList);
            }
        } else {
            wrongInput();
            chooseRequired(subjectList);
        }
        OutputView.addSubjectComplete();
    }

    // 추가 : 선택과목
    private void chooseOptional(List<Subject> subjectList) {
        OutputView.optionalScreen();
        Set<Integer> selectedSubjects = new HashSet<>();
        String optionalSubject = InputView.getUserStringInputNoMessageNextline().replaceAll(" ", "");

        if (isValidSelectionO(optionalSubject)) {
            selectedSubjects.clear();
            if (optionalSubject.split(",").length != 2) {
                OutputView.wrongInputOptionalSubject();
                chooseOptional(subjectList);

            } else if (optionalSubject.split(",").length == 2) {
                String[] subjectArray = optionalSubject.split(",");
                int[] subjectArrayNum = new int[subjectArray.length];

                for (int i = 0; i < subjectArray.length; i++) {
                    subjectArrayNum[i] = Integer.parseInt(subjectArray[i]);
                    selectedSubjects.add(subjectArrayNum[i]);
                }
                if (selectedSubjects.size() != subjectArrayNum.length) {
                    OutputView.wrongInputSubject();
                    System.out.println();
                    chooseOptional(subjectList);
                } else {
                    for (int i = 0; i < subjectArray.length; i++) {
                        subjectList.add(repository.findAllSubject().get(subjectArrayNum[i] + 4));
                    }
                }
            } else {
                wrongInput();
                chooseOptional(subjectList);
            }

        } else {
            wrongInput();
            chooseOptional(subjectList);
        }
        OutputView.addSubjectComplete();
    }

    // 추가 : 잘못된 입력
    private void wrongInput() {
        OutputView.wrongInputScreen();
    }

    // 추가 : 필수 Subject 검증
    private boolean isValidSelectionR(String input) {
        return input.matches("[1-5,]+");
    }

    // 추가 :선택 Subject 검증
    private boolean isValidSelectionO(String input) {
        return input.matches("[1-4,]+");
    }

    // 추가 : 메인으로 이동
    private void backToMain() {
        OutputView.backToMainScreen();
        try {
            Thread.sleep(3000);
            showMainMenu();
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
                showSubjectRoundGrade(studentId); //3-2.(과목별) 회차별 등급 조회
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
                //일단 넣어놓음 변경해야함 (수정함)
                OutputView.wrongInputStudentId();
            }
        }
        while (true) {
            inputRound = InputView.getRoundByUser();
            if (isValidRound(inputRound)) {
                if (!repository.isScoreRecordExist(studentId, inputSubjectId, inputRound)) {
                    //유저에게 점수 입력받기
                    int inputScore = InputView.getScoreByUser();
                    //점수 검증절차 넣어야함.(if else로 점수 검증 절차 수정)
                    if (isValidScore(inputScore)) {
                        Student student = repository.findStudentById(studentId);
                        Subject subject = repository.findSubjectById(inputSubjectId);
                        repository.addTestScore(student, subject, inputRound, inputScore);
                        OutputView.completeSetScore();
                        OutputView.delayChangeScreen();
                        selectStudentManageInfo(studentId);
                        break;
                    } else {
                        OutputView.showWrongAddContext();
                    }

                } else {
                    OutputView.duplicateRound();
                }
            } else {
                //수정해야함.
                OutputView.wrongInputRound();
            }
        }
    }

    //3-2. (과목별) 회차별 등급 조회
    public void showSubjectRoundGrade(int studentId) {
        OutputView.showQuerySubjectRoundGradeScreenInput(repository.findStudentById(studentId).getSubjectList());
        int input = 0;
        while(true) {
            input = InputView.getUserIntInputNoMessage();
            if(repository.hasSubject(studentId,input)) {
                break;
            }
            OutputView.printNotSubjectOfStudent();
        }
        String subjectName = repository.findSubjectNameById(input);
        List<Score> scoreList = repository.findGradeByIdAndName(studentId, subjectName);
        if(scoreList.isEmpty()) {
            OutputView.printNoScoreRecord();
            OutputView.delayChangeScreen();
            selectStudentManageInfo(studentId);
        } else {
            OutputView.printRoundScore(scoreList);
        }
        while (true) {
            int backinput = InputView.getUserIntInputNoMessage();
            if (backinput == 0) {
                selectStudentManageInfo(studentId);
                break;
            } else {
                OutputView.wrongInputScreen();
            }
        }
    }

    //3-3. (과목별) 시험 회차 점수 수정 메뉴 진입
    public void showAndSelectSubject(Student student) {
        // 유효성 검사 메서드 필요함 hasSubject
        // 과목 목록 출력
        OutputView.showStudentSubjectList(student.getStudentName(), student.getSubjectList());

        while (true) {
            int subjectId = InputView.getUserIntInput();
            //0 입력 시 3.메뉴화면으로 이동
            if (subjectId == 0) {
                selectStudentManageInfo(student.getStudentId());
                break;
            }
            //입력받은 과목이 학생이 신청한 과목인지 검증
            else if (repository.hasSubject(student.getStudentId(), subjectId)) {
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

        // 회차 선택.cl
        int roundNumber;
        while (true) {
            roundNumber = InputView.getUserIntInput();

            if (roundNumber == 0) {
                selectStudentManageInfo(studentId); // 이전 메뉴로 돌아가기
                return; // 함수 종료
            }

            if (isValidRound(roundNumber)) {
                int finalRoundNumber = roundNumber;
                Score scoreToUpdate = scores.stream()
                        .filter(score -> score.getRound() == finalRoundNumber)
                        .findFirst()
                        .orElse(null);

                if (scoreToUpdate == null) {
                    OutputView.showError("선택한 회차가 목록에 없습니다. 다시 입력하거나 0을 입력하여 이전 메뉴로 돌아갑니다.");
                    continue; // 새로운 회차 번호로 다시 시도
                }

                // 새로운 점수 입력
                OutputView.promptForScoreUpdate();
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
                repository.updateTestScore(studentId, subjectId, roundNumber, newScore);
                OutputView.showConfirmUpdateStudentScore();
                break; // 회차 선택 및 점수 업데이트 완료
            } else {
                OutputView.showError("회차 번호는 1에서 10 사이여야 합니다.");
            }
        }

        // 점수 수정 후 점수 관리로 돌아감
        selectStudentManageInfo(studentId);
    }

    private boolean isValidRound(int round) {
        return round >= 1 && round <= 10; // 유효한 범위 안에 있을 때 true
    }

    private boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }
}