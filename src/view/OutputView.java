package view;

import java.util.List;

public class OutputView {

    // 수강생 등록 화면 . 1
    public static void addMemberScreen() {
        System.out.println("|]=======[ Spring Track 수강생 등록 ]=======[|");
        System.out.print("등록하시려는 수강생 이름을 입력해주세요. : ");
    }

    // 수강생 이름 입력 완료
    public static void addNameComplete() {
        System.out.println("수강생 이름이 입력되었습니다.");
    }

    public static void addSubjectComplete() {
        System.out.println("과목이 입력되었습니다.");
    }

    public static void wrongInputScreen() {
        System.out.println("잘못된 입력입니다.");
    }

    // 수강생 등록 완료 화면 . 2
    public static void requiredScreen() {
        System.out.print("|]=======[ Spring Track 필수과목 등록 ]=======[|");
        System.out.println("1. Java");
        System.out.println("2. 객체지향");
        System.out.println("3. Spring");
        System.out.println("4. JPA");
        System.out.println("5. MySQL");
        System.out.print("위 필수과목중 3가지를 골라 번호를 입력해주세요. : ");
    }

    public static void optionalScreen() {
        System.out.print("|]=======[ Spring Track 선택과목 등록 ]=======[|");
        System.out.println("1. 디자인패턴");
        System.out.println("2. Spring Security");
        System.out.println("3. Redis");
        System.out.println("4. MongoDB");
        System.out.print("위 필수과목중 2가지를 골라 번호를 입력해주세요. : ");
    }

    public static void wrongInputRequiredSubject() {
        System.out.println("3개 이상의 과목을 입력해주세요.");
    }

    public static void wrongInputOptionalSubject() {
        System.out.println("2개 이상의 과목을 입력해주세요.");
    }

    public static void completeAddStudentScreen() {
        System.out.println("수강생 등록이 완료되었습니다.");
    }

    public static void backToMainScreen() {
        System.out.println("메인 화면으로 돌아갑니다.");
    }
}


