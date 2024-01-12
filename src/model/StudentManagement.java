package model;

import java.util.List;
import java.util.Scanner;

public class StudentManagement {

    MemoryRepository repository = MemoryRepository.getInstance();

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
                        System.out.println(" 수강생ID : " + value.getStudentId() + "\n수강생이름 : " + value.getMemberName() + "'\n수강신청한과목 : " + value.getSubjectList().toString());
                    }
                }
                break;
            case 0:
                //수강생 관리 프로그램 메인 화면으로 돌아가기
            default:
                OutputView.failShowStudentList();
        }
    }
}