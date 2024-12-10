package oopprojectdraft;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String name;
    private String surname;
    private List<Course> courses;

    public Teacher(String name, String surname) {
        this.name = name;
        this.surname=surname;
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    
    public String getSurname() {
    	return surname;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
	
	

	public String getDepartment() {
		return null;
	}
}

