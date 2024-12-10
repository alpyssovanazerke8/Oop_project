package oopprojectdraft;

public class Mark {
    private Course course;
    private Student student;
    private double value;

    public Mark(Course course, Student student, double value) {
        this.course = course;
        this.student = student;
        this.value = value;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", course.getName(), value);
    }
}