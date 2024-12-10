package utils;


import enums.*;
import users.Student;
import utils.*;
import java.io.Serializable;
import java.util.*;
import database.Database;

public class Course extends CourseBase implements Serializable, Comparable<CourseBase>{
    private Vector<Lesson> lessons = new Vector<Lesson>();
    private Period period;
    private int year;
    private HashMap<Student, GradeBook> gradeBook = new HashMap<Student,GradeBook>();
    private int studentLimit;


    public Course() {

    }
    public Course(String code, String name, int ects, HashMap<School, CourseType> courseType, int limit) {
        super(code, name, ects, courseType);
        this.studentLimit=limit;
        this.period=Database.DATA.getPeriod();
        this.year=Database.DATA.getYear();
    }

    public Vector<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Vector<Lesson> lessons){
        this.lessons = lessons;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public HashMap<Student, GradeBook> getGradeBook() {
        return gradeBook;
    }

    public void setGradeBook(HashMap<Student, GradeBook> gradeBook) {
        this.gradeBook = gradeBook;
    }

    public int getStudentLimit() {
        return studentLimit;
    }

    public void setStudentLimit(int studentLimit) {
        this.studentLimit = studentLimit;
    }

    public Vector<Student> getEnrolledStudents(){
        Vector<Student> students = new Vector<Student>();
        students.addAll(gradeBook.keySet());
        return students;
    }

    public int compareTo(CourseBase o) {
        if(o instanceof Course) {
            Course c = (Course)o;
            if(year==c.getYear()) {
                return -period.compareTo(c.getPeriod());
            }
            return -Integer.compare(year, c.getYear());
        }
        return super.compareTo(o);
    }
}
