package view;

public class OutputView {

    public static void incorrectMenu() {
        System.out.println("잘못된 메뉴 선택입니다. 다시 입력해 주세요.");
    };

    public static void printMainMenu() {
        System.out.println("|]=======[ Spring Track 수강생 관리 프로그램 ]=======[|");
        System.out.println("1. 수강생 등록");
        System.out.println("2. 수강생 목록 확인");
        System.out.println("3. 수강생 점수 관리");
        System.out.println();
    }
}
