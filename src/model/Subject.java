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


    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }


    public String getSubjectName() {
        return subjectName;
    }


    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
