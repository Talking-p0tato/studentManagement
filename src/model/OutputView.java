package model;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class OutputView {

    //3. 수강색 목록 확인 화면
    public static void showStudentList() {
        System.out.println("|]=======[ Spring Track 수강생 목록 조회 ]=======[|");
        System.out.println("1. 전체 수강생목록확인");
        System.out.println("0. 돌아가기");
    }

    //3. 수강생 목록 확인 화면 (오류시)
    public static void failShowStudentList() {
        System.out.println("1과 0 중에서 다시 선택해주세요.");
    }
}