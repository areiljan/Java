package ee.taltech.iti0202.api;

import com.google.gson.Gson;
import ee.taltech.iti0202.api.school.School;
import ee.taltech.iti0202.api.student.Grade;
import ee.taltech.iti0202.api.student.Student;

import java.util.ArrayList;
import java.util.List;

public class SchoolDatabase {
    private Gson gson = new Gson();

    //DO NOT CHANGE
    public List<School> schools = new ArrayList<>();

    public SchoolDatabase(String jsonContent) {
        //DO NOT CHANGE
        loadDatabase(jsonContent);
    }

    private void loadDatabase(String jsonContent) {
        //TODO
        //Parse jsonContent string to schools list
        //
        //Make sure to set static nextId in Student's class to current highest id
        //Student.nextId = studentCount;
        schools = List.of(gson.fromJson(jsonContent, School[].class));
//        for (School school : schools) {
//            for (Student student : school.getStudents()) {
//                for (Grade grade : student.getGrades()) {
//
//                }
//            }
//        }
        System.out.println(schools);
    }

    /**
     * Get the student grades by id.
     * @param studentId - id to get grades with.
     * @return - list of grades.
     */
    private List<Grade> getStudentGrades(Integer studentId) {
        List<Grade> grades = new ArrayList<>();
        for (School school : schools) {
            for (Student student : school.getStudents()) {
                if (student.getId() == studentId) {
                    grades.addAll(student.getGrades());
                }
            }
        }
        return grades;
    }

    /**
     * Get all students in this school.
     * @param schoolName - name to get students with.
     * @return - list of students.
     */
    private List<Student> getSchoolStudents(String schoolName) {
        List<Student> students = new ArrayList<>();
        for (School school : schools) {
            if (school.getName().equals(schoolName)) {
                students.addAll(school.getStudents());
            }
        }
        return students;
    }


    /**
     * Endpoints (note, all results should be in json except the 404)
     * - /student/grades?studentId=studentId - get student's grades by id (return json array of grade classes)
     * - /school/students?schoolName=schoolName - get all students in school by schoolNam (return all fields except grades in Student class)
     *  - /schools - return all school names (return json array of just school's names)
     *  If school name or student's id doesn't exist, return string 404
     * @param path - endpoint path
     * @return - result, if there is no result, return 404 in string
     */
    public String get(String path) {
        String[] parts = path.split("\\?");
        String endpoint = parts[0];
        switch (endpoint) {
            case "/student/grades":
                int studentId = Integer.parseInt(parts[1].split("=")[1]);
                List<Grade> grades = getStudentGrades(studentId);
                return gson.toJson(grades);
            case "/school/students":
                String schoolName = parts[1].split("=")[1];
                List<Student> students = getSchoolStudents(schoolName);
                if (students.isEmpty()) {
                    return "404";
                }
                return gson.toJson(students);
            case "/schools":
                List<String> schoolNames = schools.stream()
                        .map(School::getName)
                        .toList();
                return gson.toJson(schoolNames);
            default:
                return "404";
        }
    }

    /**
     * Add the student to the school.
     *
     * @return true if successful.
     */
    private boolean addStudentToSchool(String schoolName, String studentName) {
        for (School school : schools) {
            if (school.getName().equals(schoolName)) {
                school.addStudent(new Student(studentName));
                return true;
            }
        }
        return false;
    }

    /**
     * Add the grade to the student.
     *
     * @return true if successful.
     */
    private boolean addGradeToStudent(Integer studentId, Integer grade, String assignment) {
        for (School school : schools) {
            for (Student student : school.getStudents()) {
                if (student.getId() == studentId) {
                    student.addGrade(new Grade(grade, assignment));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Endpoints
     * - /school/student?schoolName=schoolName&studentName=studentName - add new student to school
     * - /student/grade?studentId=studentId&grade=grade&gradeAssignment=assignment - add new grade to student
     * @param path - endpoint path
     * @return result, if post was successful or not, for example if school or student doesn't exist, should return false
     */
    public boolean post(String path) {
        String[] parts = path.split("\\?");
        String endpoint = parts[0];

        switch (endpoint) {
            case "/school/student":
                String[] params = parts[1].split("&");
                String schoolName = params[0].split("=")[1];
                String studentName = params[1].split("=")[1];
                return addStudentToSchool(schoolName, studentName);
            case "/student/grade":
                String[] gradeParams = parts[1].split("&");
                int studentId = Integer.parseInt(gradeParams[0].split("=")[1]);
                int grade = Integer.parseInt(gradeParams[1].split("=")[1]);
                String assignment = gradeParams[2].split("=")[1];
                return addGradeToStudent(studentId, grade, assignment);
            default:
                return false;
        }
    }

    /**
     * Update the specific students name.
     * @param studentId - the students whose name to update id.
     * @param studentName - new name.
     * @return - true if successful.
     */
    private boolean updateStudentName(Integer studentId, String studentName) {
        for (School school : schools) {
            for (Student student : school.getStudents()) {
                if (student.getId() == studentId) {
                    student.setName(studentName);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Endpoints
     * - /student/name?studentId=studentId&name=newName - change student's name
     * @param path - endpoint path
     * @return result, if put was successful or not, for example if student doesn't exist, should return false
     */
    public boolean put(String path) {
        String[] parts = path.split("\\?");
        String endpoint = parts[0];

        switch (endpoint) {
            case "/student/name":
                String[] params = parts[1].split("&");
                int studentId = Integer.parseInt(params[0].split("=")[1]);
                String newName = params[1].split("=")[1];
                return updateStudentName(studentId, newName);
            default:
                return false;
        }
    }

    /**
     * Delete student with this id.
     * @param studentId - id of the student to delete.
     * @return - true if successful.
     */
    private boolean deleteStudent(Integer studentId) {
        for (School school : schools) {
            for (Student student : school.getStudents()) {
                if (student.getId() == studentId) {
                    school.removeStudent(student);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Endpoints
     * - /student/{studentId} (for example /student/10 ) - delete student from the database
     * @param path - endpoint path
     * @return result, if delete was successful or not, for example if student doesn't exist, should return false
     */
    public boolean delete(String path) {
        String endpoint = path;

        switch (endpoint) {
            case "/student/":
                String[] parts = path.split("/");
                Integer.parseInt(parts[parts.length - 1]);
                int studentId = Integer.parseInt(parts[parts.length - 1]);
                return deleteStudent(studentId);
            default:
                return false;
        }
    }
}
