package ee.taltech.iti0202.json.student;

public class Grade {
    private int grade;
    private String assignment;

    /**
     * Grade constructor.
     * @param grade - grade.
     * @param assignment - assignment.
     */
    public Grade(int grade, String assignment) {
        this.grade = grade;
        this.assignment = assignment;
    }

    /**
     * Grade getter.
     * @return - grade.
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Assignment getter.
     * @return - assignment.
     */
    public String getAssignment() {
        return assignment;
    }
}
