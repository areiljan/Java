package ee.taltech.iti0202.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ee.taltech.iti0202.json.school.School;
import ee.taltech.iti0202.json.student.Grade;
import ee.taltech.iti0202.json.student.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SchoolDatabase {
    /*** DO NOT CHANGE */
    private final List<School> schools = new ArrayList<>();
    private final Gson gson;

    /**
     * DO NOT CHANGE
     * @param school school to add
     */
    public void addSchool(School school) {
        this.schools.add(school);
    }

    /**
     * DO NOT CHANGE
     * @return schools in the db
     */
    public List<School> getSchools() {
        return this.schools;
    }

    public SchoolDatabase() {
        this.gson = new Gson();
    }
    /**
     * Get all students in all schools in the database
     * @return all students json, if it's empty, return empty json {}
     */
    public String getAllStudents() {
        List<Student> students = schools.stream()
                .flatMap(school -> school.getStudents().stream())
                .collect(Collectors.toList());
        if (students.isEmpty()) {
            return "{}";
        }

        return gson.toJson(students);
    }

    /**
     * Get all students in specific school
     * @param school school's students to get
     * @return school's students json, if it's empty, return empty json {}
     */
    public String getAllStudents(School school) {
        List<Student> students = school.getStudents();
        if (students.isEmpty()) {
            return "{}";
        }
        return gson.toJson(students);
    }

    /**
     * Helper method to get a list of student names in a specific school.
     * @param school - where to get the names.
     * @return - List of names as strings.
     */
    private List getAllStudentNames(School school) {
        List<Student> students = school.getStudents();
        List<String> names = new ArrayList<>();
        for (Student student : students) {
             names.add(student.getName());
        }
        return names;
    }

    /**
     * Get student by id, check all schools that are in the database
     * @param id student's id
     * @return student class's json, if student is not found, return empty json {}
     */
    public String getStudent(int id) {
        for (Student student : getAllStudentsAsList()) {
            if (student.getId() == id) {
                return gson.toJson(student);
            }
        }
        return "{}";
    }

    /**
     * Convert student object to JSON string using Gson.
     */
    public String convertToJson(Student student) {
        return gson.toJson(student);
    }

    /**
     * Get student's grades by id
     * @param id student's id
     * @return student's name with key "name", and array of grades (Grade class) with key "grades" in json,
     * if student is not found, return empty json {}
     */
    public String getStudentGrades(int id) {
        Gson gson = new Gson();
        for (Student student : getAllStudentsAsList()) {
            if (student.getId() == id) {
                Map<String, Object> studentGrades;
                studentGrades = new HashMap<>();
                studentGrades.put("name", student.getName());
                studentGrades.put("grades", student.getGrades());
                return gson.toJson(studentGrades);
            }
        }
        // Student not found, return empty JSON object
        return "{}";
    }

    /**
     * Get all students in all schools.
     * @return - list of students in schools.
     */
    private List<Student> getAllStudentsAsList() {
        List<Student> students = new ArrayList<>();
        for (School school : schools) {
            for (Student student : school.getStudents()) {
                students.add(student);
            }
        }
        return students;
    }


    /**
     * Get student's average grade by id
     *
     * @param id student's id
     * @return student's name with key "name", and average grade with key "averageGrade" in json,
     * if student is not found, return empty json {}
     */
    public String getStudentAverageGrade(int id) {
        String studentJson = getStudent(id);
        Student student = gson.fromJson(studentJson, Student.class);

        if (student == null) {
            return gson.toJson(new HashMap<>());
        }

        double average = 0;
        List<Grade> gradesList = student.getGrades();
        if (gradesList != null && gradesList.isEmpty()) {
            List<Integer> grades = student.getGrades().stream()
                    .map(Grade::getGrade)
                    .toList();
            average = grades.stream()
                    .mapToDouble(Integer::doubleValue)
                    .average()
                    .orElse(0); // Return 0 if the list is empty
        }

        Map<String, Object> averageGrades = new HashMap<>();
        averageGrades.put("name", student.getName());
        averageGrades.put("averageGrades", average);
        return gson.toJson(averageGrades);
    }

    /**
     * Get average grade in each school in the database
     * @return json array of [{"school": "school's name", "averageGrade": averageGrade double}, ...],
     * if no schools are in the db, return empty json {}
     */
    public String getAverageGradeInEachSchool() {
        Map<String, Object> averageGradesPerSchool = new HashMap<>();
        for (School school : schools) {
            averageGradesPerSchool.put("school", school.getName());
            averageGradesPerSchool.put("averageGrade", averageGrade(school));
        }
        if (averageGradesPerSchool.isEmpty()) {
            return "{}";
        }
        return gson.toJson(averageGradesPerSchool);
    }

    /**
     * Helper method to get the average grade of a school.
     * @param school - school to get the average grade of.
     * @return - average grade of the school.
     */
    private Double averageGrade(School school) {
        List<Student> students = getAllStudentsAsList();
        List<Double> studentAverageGrades = new ArrayList<>();
        for (Student student : students) {
            JsonElement jsonElement = JsonParser.parseString(getStudentAverageGrade(student.getId()));
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.get("averageGrade") != null) {
                double averageGrade = jsonObject.get("averageGrade").getAsDouble();
                studentAverageGrades.add(averageGrade);
            }
        }
        return studentAverageGrades.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .getAsDouble();
    }

    /**
     * Get average grade for each student in each school in the database
     * @return json array of [{"school": "school's name", "grades": [{"student": "student's name","averageGrade": averageGrade double}]}, ...],
     * if no schools are in the db, return empty json {}
     */
    public String getAllStudentsInEachSchoolAndTheirAverageGrades() {
        List<Map<String, Object>> schoolsAndStudents = new ArrayList<>();
        for (School school : schools) {
            Map<String, Object> schoolsAndStudentGrades = new HashMap<>();
            schoolsAndStudentGrades.put("school", school.getName());
            schoolsAndStudentGrades.put("grades", getStudentsWithAverageGradesList(school));
            schoolsAndStudents.add(schoolsAndStudentGrades);
        }
        if (schoolsAndStudents.isEmpty()) {
            return "{}";
        }
        return gson.toJson(schoolsAndStudents);
    }

    /**
     * Helper method to get the list of students with average grades.
     * @param school - which schools students.
     * @return - list of students and their average grades.
     */
    private List getStudentsWithAverageGradesList(School school) {
        List<Map<String, Object>> studentsWithAverageGrade = new ArrayList<>();
        for (Student student : school.getStudents()) {
            Map<String, Object> studentData = new HashMap<>();

            JsonElement jsonElement = JsonParser.parseString(getStudentAverageGrade(student.getId()));
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            if (jsonObject.get("averageGrade") != null) {
                double averageGrade = jsonObject.get("averageGrade").getAsDouble();
                studentData.put("student", student.getName());
                studentData.put("averageGrade", averageGrade);
                studentsWithAverageGrade.add(studentData);
            }
        }
        return studentsWithAverageGrade;
    }

    /**
     * Get all student's names in each school
     * @return json array of [{"school": "school's name", "students": ["student1", "student2", ...]}, ...],
     * if no schools are in the db, return empty json {}
     */
    public String getAllStudentsNamesInEachSchool() {
        List<Map<String, Object>> schoolsAndStudents = new ArrayList<>();
        for (School school : schools) {
            Map<String, Object> schoolAndStudents = new HashMap<>();
            schoolAndStudents.put("school", school.getName());
            schoolAndStudents.put("students", getAllStudentNames(school));
            schoolsAndStudents.add(schoolAndStudents);
        }
        if (schoolsAndStudents.isEmpty()) {
            return "{}";
        }
        return gson.toJson(schoolsAndStudents);
    }

    /**
     * Get average grade and all given grades count from all schools in the database
     * @return json of {"averageGrade": averageGradeDouble, "gradesTotal": gradesTotalInt}
     */
    public String getAverageGradeAndGradesCountGlobally() {
        List<Student> students = getAllStudentsAsList();
        int gradeCount = 0;
        for (Student student : students) {
            List<Grade> grades = student.getGrades();
            gradeCount += grades.size();
        }

        double averageGrade = students.stream()
                .flatMap(student -> student.getGrades().stream())
                .mapToDouble(Grade::getGrade)
                .average()
                .orElse(0.0);
        Map<String, Object> averageGradeWithCount = new HashMap<>();
        averageGradeWithCount.put("averageGrade", averageGrade);
        averageGradeWithCount.put("gradesTotal", gradeCount);
        return gson.toJson(averageGradeWithCount);
    }
}
