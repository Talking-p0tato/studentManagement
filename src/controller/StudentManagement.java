package controller;

import model.MemoryRepository;
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
}
