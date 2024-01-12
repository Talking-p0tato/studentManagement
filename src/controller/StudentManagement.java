package controller;

import model.MemoryRepository;
import model.Student;
import model.Subject;
import view.InputView;
import view.OutputView;


public class StudentManagement {

    MemoryRepository repository = MemoryRepository.getInstance();

    public StudentManagement() {
    }

    public void start() {
        showMainMenu();
    }

    public void showMainMenu() {
        OutputView.printMainMenu();
        while(true) {
            int input = InputView.getUserIntInput();
            switch(input) {
                case 1 : //수강생 등록 메서드
                case 2 : showStudentInfo();
                case 3 : //수강생 점수 관리 메서드
                default : OutputView.incorrectMenu();
            }
        }
    }

    public void showStudentInfo() {
        OutputView.showStudentList();
        while(true) {
            int input = InputView.getUserIntInput();
            switch (input) {
                case 1:
                    OutputView.printAllStudent(repository.findAllStudent());
                    break;
                case 0:
                    showMainMenu();
                    break;
                default:
                    OutputView.failShowStudentList();
            }
        }
    }

    /**
     * 회차별 점수 등록 메서드
     */
    public void showSetRoundScore(int studentId) {
        int inputSubjectId;
        int inputRound;
        //과목출력 메서드
        while(true) {
            inputSubjectId = InputView.getUserIntInput();
            if(repository.hasSubject(studentId,inputSubjectId)) {
                break;
            } else {
                //학생이 선택한 과목 아님
                //OutputView.showError
            }
        }
        while(true) {
            inputRound = InputView.getUserIntInput();
            //round검증해야함.
            if(!repository.isScoreRecordExist(studentId, inputSubjectId, inputRound)) {
                //유저에게 점수 입력받기
                int inputScore = InputView.getUserIntInput();
                //점수 검증절차 넣어야함.
                Student student = repository.findStudentById(studentId);
                Subject subject = repository.findSubjectById(inputSubjectId);
                repository.addTestScore(student, subject, inputRound, inputScore);
                OutputView.completeSetScore();
                OutputView.delayChangeScreen();
                //점수관리 화면으로 이동
                break;
            } else {
                OutputView.duplicateRound();
            }
        }
    }
}
