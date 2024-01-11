package model;


public class Score {
    private Student student;
    private Subject subject;
    private int round;
    private int score;
    private String grade;

    public Score(Student student, Subject subject, int round, int score, String grade) {
        this.student = student;
        this.subject = subject;
        this.round = round;
        this.score = score;
        this.grade = grade;
    }
}
