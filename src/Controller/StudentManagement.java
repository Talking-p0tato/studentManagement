package Controller;

import View.OutputView;
import model.MemoryRepository;
import model.Score;
import model.Student;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
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
                showStudentInfo(); //잘못 입력했을시 수강생 목록화면 다시 출력
        }
    }

    //회차별 등급 조회
    //🔵int number = 이전에 입력받은 수강생 ID🔵
    public void showGradeByRound(int number, int subjectId) {
        System.out.println("조회하고자 하는 과목ID를 입력해주세요.");
        while (true) {
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();

            if (input >= 1 && input <= 9) {
                OutputView.printGradeByRound(input, subjectId);

                System.out.println("0. 수강생 점수 관리 화면으로 가기");
                int zero = sc.nextInt();
                getReturn(zero);
                break;
            } else {
                OutputView.printErrorGradeByRound();
            }
        }
    }

    private static void getReturn(int zero) {
        while (true) {
            switch (zero) {
                case 0:
                    //수강생 점수 관리 화면으로 가기
                    break;
                default:
                    System.out.println("잘못 입력 하셨습니다.");
            }
        }
    }
}