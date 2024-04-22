package ee.taltech.iti0202.schools;

import ee.taltech.iti0202.location.Location;
import ee.taltech.iti0202.student.Student;

import java.util.ArrayList;
import java.util.List;
public abstract class School implements Comparable<School> {
    private final String name;
    private final Location location;
    private ArrayList<Student> studentList;
    private static ArrayList<School> schoolList = new ArrayList<>();
    /**
     * Construct a new school with a name and Location.
     * @param name name of school
     * @param location Location of school
     */
    protected School(String name, Location location) {
        this.name = name;
        this.location = location;
        this.studentList = new ArrayList<>();
        // the schoolList will only be initialized once (because it is static)
        addSchool(this);
    }

    /**
     * Adds student to list of students
     * @param student Student
     */
    public void addStudent(Student student) {
        if (!studentList.contains(student)) {
            studentList.add(student);
        }
    }

    /**
      * Returns name of school
      * @return name
     */
    public String getName() {
        return name;
    }

    /**
      * Returns location of school
      * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
      * Returns List of students in school
      * @return List of students
     */
    public List<Student> getStudents() {
        return studentList;
    }

    /**
     * Comparing order:
     *  1. By class
     *  2. By amount of student
     *  3. By country name
     *  4. By city name
     *  5. By school name
     * @param o the object to be compared.
     * @return int -1, 0, or 1
     */
    @Override
    public int compareTo(School o) {
        // Comparing school types
        int typeComparison = getTypePriority(this) - getTypePriority(o);
        if (typeComparison != 0) {
            return typeComparison;
        }

        // Comparing amount of students
        if (this.studentList.size() != o.getStudents().size()) {
            return Integer.compare(o.getStudents().size(), this.studentList.size());
        }

        // Comparing country names
        int countryComparison = location.country().compareTo(o.getLocation().country());
        if (countryComparison != 0) {
            return countryComparison;
        }

        // Comparing city names
        int cityComparison = location.city().compareTo(o.getLocation().city());
        if (cityComparison != 0) {
            return cityComparison;
        }

        // Comparing school names
        return this.name.compareTo(o.getName());
    }

    // Helper method to determine school type priority
    private int getTypePriority(School school) {
        if (school instanceof PrimarySchool) {
            return 5;
        } else if (school instanceof SecondarySchool) {
            return 4;
        } else if (school instanceof University) {
            return 3;
        } else {
            return 0;
        }
    }

    /**
     * Adds given school to a list containing all Schools.
     * Does not add it to list if it's already added.
     * @param school School
     */
    public static void addSchool(School school) {
        if (!schoolList.contains(school)) {
            schoolList.add(school);
        }
    }

    /**
     * Clears list containing all schools
     */
    public static void clearSchools() {
        if (schoolList != null) {
            schoolList.clear();
        }
    }

    /**
     * Returns sorted List of all schools.
     * @return sorted list of schools
     */
    public static List<School> getSchools() {
        schoolList.sort(null);
        return schoolList;
    }
}
