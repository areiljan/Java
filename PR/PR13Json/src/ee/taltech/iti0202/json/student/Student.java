package ee.taltech.iti0202.json.student;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private static int nextId;

    /**
     * Increment student id.
     * @return - id.
     */
    private static int getAndIncrementNextId() {
        return ++nextId;
    }

    private final int id;
    private final String name;
    private final List<Grade> grades = new ArrayList<>();

    /**
     * Student constructor.
     * @param name - student name.
     */
    public Student(String name) {
        this.id = getAndIncrementNextId();
        this.name = name;
    }

    /**
     * Add a grade to the student.
     * @param grade - student grade.
     */
    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

    /**
     * Get the name of the student.
     * @return - name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Id getter.
     * @return - get id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Grades getter.
     * @return - grades as list.
     */
    public List<Grade> getGrades() {
        return this.grades;
    }
}
