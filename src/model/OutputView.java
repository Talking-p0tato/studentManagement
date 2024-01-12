package model;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

import java.util.stream.IntStream;
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

    //4. 수강생 점수관리 메인 화면 출력
    public void showAddStudentIdScreen() {
        System.out.println("|]=======[ Spring Track 수강생 점수 관리 ]=======[|" + "\n");
        System.out.println("수강생 ID 를 입력해주세요 ");
        System.out.println("->");
        //수강생 ID 인풋 받기 받은 후에 아래화면 출력
        //등록되지 않은 수강생의 ID 또는 다른 문자 입력시
        //System.out.println("잘못된 입력입니다. 다시입력해주세요.") 출력 후 -> 수강생 ID 입력화면
    }

    public void showStudentScoreManageInfo(){
        System.out.println("|]=======[ Spring Track 수강생 점수 관리 ]=======[|"+"\n");
        System.out.println("1. (과목별) 시험 회차 및 점수 등록");
        System.out.println("2. (과목별) 시험 회차별 등급 조회 (수강생의 특정 과목 시험 회차별 등급을 조회할 수 있습니다.)");
        System.out.println("3. (과목별) 시험 회차 점수 수정");
        System.out.println("0. 메인 메뉴로 이동"+"\n");
        System.out.print("원하시는 작업 메뉴 번호를 입력해주세요 : ");
        //상세메뉴번호 입력 인풋
        // 잘못된 번호 또는 문자 입력시 System.out.println("잘못된 입력입니다. 다시입력해주세요.") -> 상세메뉴 입력화면
    }

    //4-1 (과목별) 시험 회차 및 점수 등록 입력 받는 출력문
    //과목 고유번호 따로, 회차, 점수 별로나눠서 인풋 받기
    public void showAddSubjectRoundScoreFrontScreen(){
        System.out.println("|]----수강생 과목별 시험 회차 및 점수 등록----[|");
        System.out.println("점수를 등록할 학생 정보를 입력해 주세요.");
    }
    public void showAddSubjectNameScreen(){
        System.out.println("과목 이름을 입력해주세요.");
        System.out.println("->");
    }

    public void showAddRoundScreen(){
        System.out.println("회차를 입력해주세요.");
        System.out.println("회차는 1회차부터 10회차 까지 있습니다");
        System.out.println("->");
    }

    public void showAddScoreScreen(){
        System.out.println("점수를 입력해주세요.");
        System.out.println("점수는 1점부터 100점까지가 기준입니다.");
        System.out.println("->");
    }

    public void showWrongAddContext(){
        System.out.println("잘못된 입력입니다. 다시입력해주세요.");
    }
    public void showConfirmAddStudentScore(){
        System.out.println("정상적으로 등록 되었습니다!");
    }



}
