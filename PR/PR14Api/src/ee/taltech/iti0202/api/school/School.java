package ee.taltech.iti0202.api.school;
import ee.taltech.iti0202.api.student.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * The type School.
 */
public class School {
    private final List<Student> students = new ArrayList<>();
    private final String name;

    /**
     * Instantiates a new School.
     *
     * @param name the name
     */
    public School(String name) {
        this.name = name;
    }

    /**
     * Add student.
     *
     * @param student the student
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Remove student.
     *
     * @param student the student
     */
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    /**
     * Gets students.
     *
     * @return the students
     */
    public List<Student> getStudents() {
        return this.students;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }
}
