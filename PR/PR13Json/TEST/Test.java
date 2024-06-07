import ee.taltech.iti0202.json.SchoolDatabase;
import ee.taltech.iti0202.json.school.School;
import ee.taltech.iti0202.json.student.Grade;
import ee.taltech.iti0202.json.student.Student;
import org.testng.asserts.Assertion;


public class Test {
    @org.testng.annotations.Test
    public void randomTest() {
        SchoolDatabase schoolDatabase = new SchoolDatabase();
        School school = new School("oo");
        schoolDatabase.addSchool(school);
        Student student = new Student("Jakob");
        Grade grade = new Grade(2, "homework");
        student.addGrade(grade);
        school.addStudent(student);

        System.out.println(schoolDatabase.getStudentsWithAverageGradesList(school));
    }

    @org.testng.annotations.Test
    public void testTestRandomTest() {
    }
}
