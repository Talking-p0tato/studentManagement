package View;

import model.MemoryRepository;
import model.Score;

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
    //4-2. 회차별 등급 조회(입력값 맞을때)
    public static void printGradeByRound(int input, int number) {
        MemoryRepository repository = MemoryRepository.getInstance();
        List<Score> studentScore = repository.findAllScoreRecord(number, input);
        for (Score score : studentScore) {
            System.out.println(score.getRound() + ". 회차 등급은 : " + score.getGrade() + " 입니다.");
        }
    }
    //4-2. 회차별 등급 조회(입력값 틀릴때)
    public static void printErrorGradeByRound() {
        System.out.println("잘못 입력하셨습니다. 조회하실 과목ID를 다시 입력해주세요.");
    }
}