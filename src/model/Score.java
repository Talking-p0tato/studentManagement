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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
