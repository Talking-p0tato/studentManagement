package view;

import model.Score;

import java.util.Scanner;

public class InputView {

    static Scanner scanner = new Scanner(System.in);

    public static int getUserIntInput() {
        System.out.println("번호를 입력해주세요");
        return scanner.nextInt();
    }

    public static int getRoundByUser() {
        System.out.println("등록할 회차를 입력해주세요.");
        return scanner.nextInt();
    }

    public static int getScoreByUser() {
        System.out.println("등록할 점수를 입력해 주세요.");
        return scanner.nextInt();
    }

    public static String getUserStringInput() {
        System.out.println("입력해주세요");
        return scanner.next();
    }

    public static int getUserIntInputNoMessage() {
        return scanner.nextInt();
    }
}
