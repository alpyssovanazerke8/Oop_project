package oopprojectdraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {
    private String name;
    private String code;
    private Teacher teacher;
    private List<Student> enrolledStudents;
    private Map<Student, Mark> studentMarks;

    public Course(String name, String code, Teacher teacher) {
        this.name = name;
        this.code = code;
        this.teacher = teacher;
        this.enrolledStudents = new ArrayList<>();
        this.studentMarks = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void enrollStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }

    public void setMark(Student student, double value) {
        if (enrolledStudents.contains(student)) {
            Mark mark = studentMarks.get(student);
            if (mark == null) {
                mark = new Mark(this, student, value);
                studentMarks.put(student, mark);
            } else {
                mark.setValue(value);
            }
        }
    }

    public Mark getStudentMark(Student student) {
        return studentMarks.get(student);
    }

    public List<Student> getEnrolledStudents() {
        return new ArrayList<>(enrolledStudents);
    }

    @Override
    public String toString() {
        return name + " (" + code + ")";
    }
}