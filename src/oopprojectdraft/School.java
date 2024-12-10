package oopprojectdraft;
import java.util.ArrayList;
import java.util.List;

public enum School {
    SITE("School of Information Technology"),
    BS("Business School"),
    ISE("International School of Economics");

    private final String name;
    private final List<Course> availableCourses;

    School(String name) {
        this.name = name;
        this.availableCourses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCourse(Course course) {
        availableCourses.add(course);
    }

    public List<Course> getAvailableCourses() {
        return new ArrayList<>(availableCourses);
    }
}