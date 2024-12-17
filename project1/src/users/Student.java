package users;

import java.io.Serializable;

import enums.School;
import enums.StudentType;
import utils.Transcript;

public class Student extends User implements Serializable {

	private School school;
	private int enrolledYear;
	private StudentType studentType;
	private Transcript transcript;
	
	public Student() {
		super();
	}
	
	public Student(String firstName, String lastName) {
		super(firstName, lastName);
		this.transcript = new Transcript(this);
	}
	

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public int getEnrolledYear() {
        return enrolledYear;
    }

    public void setEnrolledYear(int enrolledYear) {
        this.enrolledYear = enrolledYear;
    }

   
    
    
    public Transcript getTranscript() {
        return transcript;
    }

    public void viewTranscript() {
        System.out.println(transcript.getFormattedTranscript());
    }

	public void setStudentType(StudentType studentType) {
		this.studentType = studentType;
		
	}
	
	public StudentType getStudentType() {
		return studentType;
	}
    
}
	
	
	
	
	
	
	

