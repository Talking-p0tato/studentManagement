package model;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class OutputView {

    //3. 수강색 목록 화면
    public void printStudentList() {
        MemoryRepository repository = MemoryRepository.getInstance();

        while (true) {
            System.out.println("|]=======[ Spring Track 수강생 목록 조회 ]=======[|");
            System.out.println("1. 전체 수강생목록확인");
//        System.out.println("2. 이름으로 수강생 조회");
//        System.out.println("3. 고유번호로 수강생 조회");
            Scanner sc = new Scanner(System.in);
            int number = sc.nextInt();

            if (number == 1) {
                //MemoryRepository의 전체수강생목록조회(findAllStudent())메서드사용
                List<Student> student = repository.findAllStudent();
                System.out.println("등록된 수강생은 " + student.size() + "명 입니다.");
                for (Student value : student) {
                    System.out.println(" 수강생ID : " + value.getStudentId() + "\n수강생이름 : " + value.getMemberName() + "'\n수강신청한과목 : " + value.getSubjectList().toString());
                }
                break;
            }
//        if(number == 2) {
//            System.out.println("조회하실 수강생 이름을 입력해주세요.");
//            Scanner na = new Scanner(System.in);
//            String sname = na.nextLine();
//
//        }
//        if(number == 3) {
//            System.out.println("조회하실 수강생 고유번호를 입력해주세요.");
//            Scanner nu = new Scanner(System.in);
//            int snumber = sc.nextInt();
//
//        }
            else {
                System.out.println("잘못 선택 하셨습니다.");
                System.out.println("처음으로 돌아갑니다.");
            }
        }
    }
}