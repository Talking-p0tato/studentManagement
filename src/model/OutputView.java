package model;

import java.util.List;
import java.util.stream.Collectors;


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
        System.out.println("0. 메인 메뉴로 이동"+"\n");
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
    public void showQuerySubjectRoundGradeInputScreen(){
        System.out.println("|]----수강생 과목 회차별 등급 조회----[|"+"\n");
        System.out.println("조회할 과목을 입력해주세요.");
        System.out.println("-> ");
        //과목 이름 or 과목 고유번호 입력

    }
    public void showQuerySubjectRoundGradeScreenOutput(String subject, List<Score> scoreList){
        //입력받은 시험 회차별 등급 출력
        List<Score> filteredList = scoreList.stream().filter(score ->subject
                .equals(score.getSubject().getSubjectName()))
                .map(score -> new Score(score.getStudent(), score.getSubject(), score.getRound(), score.getScore()))
                .collect(Collectors.toList());
        for(int i = 0; i < filteredList.size(); i++){
            Score studentScore = filteredList.get(i);
            System.out.printf("%d. %d회차 : %s"+"\n", i+1, studentScore.getRound(),studentScore.getGrade());
        }
        System.out.println("0. 돌아가기");
        System.out.println("->");
    }


    public void showStudentSubjectList(List<Subject> subjectList) {
        //수강 과목 선택 화면
        System.out.println("수강 과목");
        //수강생이 수강한 수강과목 출력 로직
        //ex)1. Java
        //2. 객체지향
        for(int i = 0; i < subjectList.size(); i ++) {
            Subject studentSubject = subjectList.get(i);
            System.out.printf("%d. %s", i + 1, studentSubject.getSubjectName());
        }
            System.out.println("0. 돌아가기");
            System.out.print("->");
            //과목 이름 인풋을 받음
            //수강생이 수강한 과목이름이 아니거나 잘못된 입력 값이라면
            //잘못된 입력입니다 다시입력해주세요. 출력 후 ->(회차 선택 화면)
            //0을 입력시 수강생 점수관리 메인화면으로 이동 -> (printStudentScoreManageScreen())
            //수강생의 수강 과목이 제대로 입력되면 아래문 출력

    }

    public void showUpdateStudentRoundGrade(String subject,List<Score> scoreList) {
        // 수정 회차 선택화면
        System.out.println("수정을 원하는 회차를 입력 해주세요.");
        System.out.println("과목명 : "+ subject);
        //ex) 1. 1회차 : 90
        //    2. 3회차 : 85
        //    3. 5회차 : 70
        for(int i = 0; i < scoreList.size(); i++){
            Score studentScore = scoreList.get(i);
            System.out.printf("%d. %d회차 : %d"+"\n" ,i+1, studentScore.getRound(), studentScore.getScore());
        }
        System.out.println("0. 돌아가기");
        System.out.println("->");
    }

    public void showConfirmUpdateStudentScore(){
        System.out.println("수정되었습니다!");
    }
}
