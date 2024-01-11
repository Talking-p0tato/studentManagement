package model;

import java.util.List;

public class MemoryRepository {

    private List<Student> studentList;
    private List<Score> scores;
    private List<Subject> subjectList;

    private static final MemoryRepository instance = new MemoryRepository();

    public MemoryRepository getInstance() {
        return instance;
    }

    private MemoryRepository() {
    }
}
