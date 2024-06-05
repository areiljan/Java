package ee.taltech.iti0202.json.school;

import ee.taltech.iti0202.json.student.Student;

import java.util.ArrayList;
import java.util.List;

public class School {

    private final List<Student> students = new ArrayList<>();
    private final String name;

    /**
     * School constructor.
     * @param name - name of the school.
     */
    public School(String name) {
        this.name = name;
    }

    /**
     * Add a student to the school.
     * @param student - student to add.
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Remove a student from the school.
     * @param student - student to remove.
     */
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    /**
     * Get list of students.
     * @return - list of students.
     */
    public List<Student> getStudents() {
        return this.students;
    }

    /**
     * Get the name of the school.
     * @return - name.
     */
    public String getName() {
        return this.name;
    }
}
