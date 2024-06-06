package ee.taltech.iti0202.api.student;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Student.
 */
public class Student {

    /**
     * The constant nextId.
     */
    public static int nextId;

    /**
     * Increment next id by one.
     * @return - incremented id.
     */
    private static int getAndIncrementNextId() {
        return ++nextId;
    }
    @Expose
    private final int id;
    @Expose
    private String name;
    private final List<Grade> grades = new ArrayList<>();

    /**
     * Student constructor.
     *
     * @param name - name of the student.
     */
    public Student(String name) {
        this.id = getAndIncrementNextId();
        this.name = name;
    }

    /**
     * Add a grade to the student
     *
     * @param grade the grade
     */
    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

    /**
     * Name getter.
     *
     * @return - name of the student.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Id getter.
     *
     * @return - id of the student.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets grades.
     *
     * @return the grades
     */
    public List<Grade> getGrades() {
        return this.grades;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
