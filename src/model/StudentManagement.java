package model;

import java.util.Optional;
import java.util.Scanner;

public class StudentManagement {

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

}
