package view;

import model.Score;

import java.util.Scanner;

public class InputView {

    static Scanner scanner = new Scanner(System.in);

    public static int getUserIntInput() {
        System.out.print("번호를 입력해주세요. : ");
        return scanner.nextInt();
    }

    public static int getRoundByUser() {
        System.out.print("등록할 회차를 입력해주세요. : ");
        return scanner.nextInt();
    }

    public static int getScoreByUser() {
        System.out.print("등록할 점수를 입력해 주세요. : ");
        return scanner.nextInt();
    }

    public static String getUserStringInput() {
        System.out.print("입력해주세요. : ");
        return scanner.next();
    }

    public static String getUserStringInputNoMessageNext() {
        String input = scanner.next();
        scanner.nextLine();
        return input;
    }

    public static String getUserStringInputNoMessageNextline() {
        String input = scanner.nextLine();
        return input;
    }

    public static int getUserIntInputNoMessage() {
        return scanner.nextInt();
    }
}
