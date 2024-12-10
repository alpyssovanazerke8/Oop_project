package utils;

import enums.CourseType;
import enums.School;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class CourseBase implements Serializable, Comparable<CourseBase> {
    private String code;
    private String courseName;
    private int credits;
    private HashMap<School, CourseType> courseType;

    public CourseBase(){
        super();
    }

    public CourseBase(String code, String courseName, int credits, HashMap<School, CourseType> courseType){
        this.code = code;
        this.courseName = courseName;
        this.credits = credits;
        this.courseType = courseType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public HashMap<School, CourseType> getCourseType() {
        return courseType;
    }

    public void setCourseType(HashMap<School, CourseType> courseType) {
        this.courseType = courseType;
    }

    @Override
    public String toString() {
        return "|" + code + "| " + courseName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        CourseBase other = (CourseBase) obj;
        return Objects.equals(code, other.code);
    }

    @Override
    public int compareTo(CourseBase o) {
        return code.compareTo(o.getCode());
    }
}
