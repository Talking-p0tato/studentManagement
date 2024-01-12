package view;

import model.Score;

import java.util.Scanner;

public class InputView {

    static Scanner scanner = new Scanner(System.in);

    public static int getUserIntInput() {
        System.out.println("입력해주세요");
        return scanner.nextInt();
    }

    public static String getUserStringInput() {
        System.out.println("입력해주세요");
        return scanner.next();
    }
}
