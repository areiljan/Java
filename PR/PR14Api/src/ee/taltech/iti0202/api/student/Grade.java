package ee.taltech.iti0202.api.student;

/**
 * The type Grade.
 */
public class Grade {
    private int grade;
    private String assignment;

    /**
     * Instantiates a new Grade.
     *
     * @param grade      the grade
     * @param assignment the assignment
     */
    public Grade(int grade, String assignment) {
        this.grade = grade;
        this.assignment = assignment;
    }

    /**
     * Gets grade.
     *
     * @return the grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Gets assignment.
     *
     * @return the assignment
     */
    public String getAssignment() {
        return assignment;
    }

}
