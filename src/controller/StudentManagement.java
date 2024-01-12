package controller;

import model.MemoryRepository;
import view.OutputView;
import model.Student;
import model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {

    MemoryRepository repository = MemoryRepository.getInstance();
    static Student student;
    Scanner scanner = new Scanner(System.in);


    // 수강생 추가 메서드
    public void addStudent() {
        // 수강생 이름 입력
        OutputView.addMemberScreen();
        String name = scanner.next();
        OutputView.addNameComplete();

        // 수강생이 수강할 과목 입력
        List<Subject> subjectList = new ArrayList<>();
        chooseRequired(subjectList);
        chooseOptional(subjectList);

        // 데이터베이스에 저장
        repository.addMember(name, subjectList);

        // 수강생 등록 완료
        completeAddStudent(name, subjectList);
        backToMain();
    }

    // 추가 : 수강생 등록 완료
    private void completeAddStudent(String name, List<Subject> subjectList) {
        OutputView.completeAddStudentScreen();
        System.out.println("수강생 번호 : " + repository.getStudentList().get(repository.getStudentList().size() - 1).getStudentId());
        System.out.println("수강생 이름 : " + name);
        System.out.println("필수 과목 : " + subjectList.subList(0, 3));
        System.out.println("선택 과목 : " + subjectList.subList(3, 5));
    }

    // 추가 : 필수과목
    private void chooseRequired(List<Subject> subjectList) {
        OutputView.requiredScreen();
        String requiredSubject = scanner.next();
        if (isValidSelectionR(requiredSubject)) {
            if (requiredSubject.split(",").length != 3) {
                OutputView.wrongInputRequiredSubject();
                OutputView.requiredScreen();
                requiredSubject = scanner.next();

            } else if (requiredSubject.split(",").length == 3) {
                String[] subjectArray = requiredSubject.split(",");
                int[] subjectArrayNum = new int[subjectArray.length];
                for (int i = 0; i < subjectArray.length; i++) {
                    subjectArrayNum[i] = Integer.parseInt(subjectArray[i]);
                    subjectList.add(repository.getSubjectList().get(subjectArrayNum[i]));
                    OutputView.addSubjectComplete();
                }
            } else {
                wrongInput();
            }
        } else {
            wrongInput();
            chooseRequired(subjectList);
        }
    }

    // 추가 : 선택과목
    private void chooseOptional(List<Subject> subjectList) {
        OutputView.optionalScreen();
        String optionalSubject = scanner.next();
        if (isValidSelectionO(optionalSubject)) {
            if (optionalSubject.split(",").length != 2) {
                OutputView.wrongInputOptionalSubject();
                OutputView.requiredScreen();
                optionalSubject = scanner.next();

            } else if (optionalSubject.split(",").length == 2) {
                String[] subjectArray = optionalSubject.split(",");
                int[] subjectArrayNum = new int[subjectArray.length];
                for (int i = 0; i < subjectArray.length; i++) {
                    subjectArrayNum[i] = Integer.parseInt(subjectArray[i]);
                    subjectList.add(repository.getSubjectList().get(subjectArrayNum[i] + 5));
                    OutputView.addSubjectComplete();
                }
            } else {
                wrongInput();
            }
        } else {
            wrongInput();
            chooseOptional(subjectList);
        }
    }

    // 추가 : 잘못된 입력
    private void wrongInput() {
        OutputView.wrongInputScreen();
        addStudent();
    }

    // 추가 : 필수 Subject 검증
    private boolean isValidSelectionR(String input) {
        return input.matches("[1-5]+");
    }

    // 추가 :선택 Subject 검증
    private boolean isValidSelectionO(String input) {
        return input.matches("[1-4]+");
    }

    // 추가 : 메인으로 이동
    public void backToMain() {
        OutputView.backToMainScreen();
        try {
            Thread.sleep(3000);
            /* 메인으로 돌아가는 메서드 */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


