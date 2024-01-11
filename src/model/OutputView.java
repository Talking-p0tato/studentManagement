package model;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {

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
        System.out.println("0. 메인 메뉴로 이동"+"\n"+"\n");
        System.out.print("원하시는 작업 메뉴 번호를 입력해주세요 : ");
        //상세메뉴번호 입력 인풋
        // 잘못된 번호 또는 문자 입력시 System.out.println("잘못된 입력입니다. 다시입력해주세요.") -> 상세메뉴 입력화면
    }

    //4-1 (과목별) 시험 회차 및 점수 등록 입력 받는 출력문
    public void showAddSubjectRoundScoreScreen(){
        System.out.println("|]----수강생 과목별 시험 회차 및 점수 등록----[|"+"\n");
        System.out.println("점수 정보를 (,)로 구분해서 입력해 주세요.");
        System.out.println("점수 정보(과목 고유 번호, 수강생 고유 번호, 회차, 점수)");
        System.out.println("-> ");
        //과목 고유번호, 수강생 고유번호, 회차, 점수 인풋 받기
        //
    }

    //4-2 (과목별) 시험 회차별 등급 조회 입력 받는 출력문
    public void showQuerySubjectRoundGradeScreen(){
        System.out.println("|]----수강생 과목 회차별 등급 조회----[|"+"\n");
        System.out.println("조회할 과목을 입력해주세요.");
        System.out.println("-> ");
        //과목 이름 or 과목 고유번호 입력

    }


}
