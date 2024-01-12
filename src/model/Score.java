package model;


public class Score {
    private Student student;
    private Subject subject;
    private int round;
    private int score;
    private String grade;

    public Score(Student student, Subject subject, int round, int score) {
        this.student = student;
        this.subject = subject;
        this.round = round;
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getRound() {
        return round;
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

    public void setGrade() {
        if(subject.getType().equals("필수")) {
            if(95<=this.score && this.score <= 100) {
                this.grade = "A";
            } else if(90<=this.score && this.score <= 94) {
                this.grade = "B";
            } else if(80<=this.score && this.score <= 89) {
                this.grade = "C";
            } else if(70<=this.score && this.score <= 79) {
                this.grade = "D";
            } else if(60<=this.score && this.score <= 69) {
                this.grade = "F";
            } else if(this.score < 60) {
                this.grade = "N";
            }
    } else if(subject.getType().equals("선택")) {
            if(90<=this.score && this.score <= 100) {
                this.grade = "A";
            } else if(80<=this.score && this.score <= 89) {
                this.grade = "B";
            }  else if(70<=this.score && this.score <= 79) {
                this.grade = "C";
            } else if(60<=this.score && this.score <= 69) {
                this.grade = "D";
            } else if(50<=this.score && this.score <=59) {
                this.grade = "F";
            } else if(this.score < 50) {
                this.grade = "N";
            }
        }
    }
}
