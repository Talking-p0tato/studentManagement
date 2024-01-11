package model;

public class Subject {
    private int subjectId;
    private String subjectName;
    private String type;

    public Subject(int subjectId, String subjectName, String type) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.type = type;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getType() {
        return type;
    }
}
