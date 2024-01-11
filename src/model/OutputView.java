package model;

public class OutputView {

    //4. 수강생 점수관리 메인 화면 출력
    // @param 수강생고유번호(student id), 메뉴선택
    public void printStudentScoreManageScreen() {
        System.out.println("|]=======[ Spring Track 수강생 점수 관리 ]=======[|"+"\n");
        System.out.println("수강생 ID 를 입력해주세요 ");
        System.out.println("->");
        //수강생 ID 인풋 받기 받은 후에 아래화면 출력
        //등록되지 않은 수강생의 ID 또는 다른 문자 입력시
        //System.out.println("잘못된 입력입니다. 다시입력해주세요.") 출력 후 -> 수강생 ID 입력화면
        System.out.println("1. (과목별) 시험 회차 및 점수 등록");
        System.out.println("2. (과목별) 시험 회차별 등급 조회 (수강생의 특정 과목 시험 회차별 등급을 조회할 수 있습니다.)");
        System.out.println("3. (과목별) 시험 회차 점수 수정");
        System.out.println("0. 메인 메뉴로 이동"+"\n"+"\n");
        System.out.print("원하시는 작업 메뉴 번호를 입력해주세요 : ");
        //상세메뉴번호 입력 인풋
        // 잘못된 번호 또는 문자 입력시 System.out.println("잘못된 입력입니다. 다시입력해주세요.") -> 상세메뉴 입력화면
    }

    //4-1 (과목별) 시험 회차 및 점수 등록 출력문
    // @param 과목 고유번호(subject id), 수강생 고유번호(student id), 회차, 점수
    public void addSubjectRoundScoreScreen(){
        System.out.println("|]----수강생 과목별 시험 회차 및 점수 등록----[|"+"\n");
        System.out.println("점수 정보를 (,)로 구분해서 입력해 주세요.");
        System.out.println("점수 정보(과목 고유 번호, 수강생 고유 번호, 회차, 점수)");
        System.out.println("-> ");
        //과목 고유번호, 수강생 고유번호, 회차, 점수 인풋 받기
        //
    }

    //4-2 (과목별) 시험 회차별 등급 조회 출력문
    //@param 과목이름(subject name) or 과목 고유번호(subject id)
    public void querySubjectRoundGradeScreen(){
        System.out.println("|]----수강생 과목 회차별 등급 조회----[|"+"\n");
        System.out.println("조회할 과목을 입력해주세요.");
        System.out.println("-> ");
        //과목 이름 or 과목 고유번호 입력

    }

    //4-3 (과목별) 회차 점수 수정 출력문
    // @ param 수강생 이름, 과목선택, 회차선택,수정 점수
    public void updateRoundScoreScreen() {
        System.out.println("|]----수강생 과목 회차 점수 수정----[|");
        //System.out.printf("수강생 이름 : %s"+"\n", );

        //수강 과목 선택 화면
        System.out.println("수강 과목");
        //수강생이 수강한 수강과목 출력 로직
        //ex)1. Java
        //2. 객체지향
        System.out.println("0. 돌아가기");
        System.out.print("->");
        //과목 이름 인풋을 받음
        //수강생이 수강한 과목이름이 아니거나 잘못된 입력 값이라면
        //잘못된 입력입니다 다시입력해주세요. 출력 후 ->(회차 선택 화면)
        //0을 입력시 수강생 점수관리 메인화면으로 이동 -> (printStudentScoreManageScreen())
        //수강생의 수강 과목이 제대로 입력되면 아래문 출력

        //회차선택 화면
        System.out.printf("과목명 : %s", /*입력받은 과목 이름*/ );
        // 선택된 과목의 등록된 회차 점수 출력 로직
        //ex)  1회차 : 95
        //     3회차 : 85
        //     4회차 : 60
        System.out.println("0. 돌아가기");
        System.out.print("-> ");
        //화차 선택을 인풋받음
        // 등록된 회차가 아닌 1미만 10초과의 인풋을 받으면
        // System.out.println(등록된 회차가 없습니다.다시 입력해주세요.) 출력후 -> 회차선택 화면
        // 점수 이외의 값을 입력받으면
        // System.out.println("잘못된 입력값입니다. 다시입력해주세요.) 출력후 -> 회차 선택 화면
        // 0을 입력시 -> 수강 과목 선택 화면으로 이동
        // 정상적인 입력을 받으면 아래 출력문출력

        System.out.println("수정할 점수를 입력해주세요 : ");
        //점수 인풋을 받음(0~100)
        //System.out.println("수정 되었습니다!"); 출력후 -> 회차선택 화면

    }

}
