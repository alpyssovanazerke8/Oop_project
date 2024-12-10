package oopprojectdraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student  {
	private String firstName;
    private String surname;
    private int id;
    private School school;
    private List<Course> enrolledCourses;
    private Transcript transcript;
    private int numberOfRetakes;
    private List<OrganizationMembership> memberships;
    private Map<Teacher, List<Integer>> teacherRatings;

    public Student(String firstName, String surname, int id, School school) {
        this.firstName = firstName;
        this.surname = surname;
        this.id = id;
        this.school = school;
        this.enrolledCourses = new ArrayList<>();
        this.transcript = new Transcript(this);
        this.numberOfRetakes = 0;
        this.memberships = new ArrayList<>();
        this.teacherRatings = new HashMap<>();
    }

    public List<Course> viewCourses() {
        List<Course> availableCourses = school.getAvailableCourses();
        System.out.println("Available courses:");
        for (int i = 0; i < availableCourses.size(); i++) {
            System.out.println((i + 1) + ". " + availableCourses.get(i));
        }
        return availableCourses;
    }

    public void registerForCourses(List<Course> availableCourses) {
        if (!availableCourses.isEmpty()) {
            Course selectedCourse = availableCourses.get(0);
            enrollInCourse(selectedCourse);
            System.out.println("Enrolled in: " + selectedCourse);
        } else {
            System.out.println("No courses available for registration.");
        }
    }

    private void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            course.enrollStudent(this);
        }
    }
    
    public void viewTeacherInfo(Course course) {
        Teacher teacher = course.getTeacher();
        System.out.println("Teacher for " + course + ":");
        System.out.println("Name: " + teacher.getName() + " " + teacher.getSurname());
        System.out.println("Department: " + teacher.getDepartment());
    }

    public void joinOrganization(Organization organization, String role) {
        memberships.add(new OrganizationMembership(organization, role));
    }

    public void viewOrganizations() {
        if (memberships.isEmpty()) {
            System.out.println(firstName + " " + surname + " is not a member of any organizations.");
        } else {
            System.out.println(firstName + " " + surname + " is a member of the following organizations:");
            for (OrganizationMembership membership : memberships) {
                System.out.println("- " + membership);
            }
        }
    }

    public void viewMarks() {
        System.out.println("Marks for " + firstName + " " + surname + ":");
        for (Course course : enrolledCourses) {
            Mark mark = course.getStudentMark(this);
            if (mark != null) {
                System.out.println(course + ": " + mark.getValue());
            } else {
                System.out.println(course + ": Not graded yet");
            }
        }
    }
    
    public String getFirstName() {
		return firstName;
	}
    
    public String getSurname() {
		return surname;
	}

    public Transcript getTranscript() {
        return transcript;
    }

    public void takeRetake(Course course) {
        if (numberOfRetakes < 3) {
            numberOfRetakes++;
            System.out.println("Retake scheduled for " + course);
        } else {
            throw new IllegalStateException("Maximum number of retakes exceeded");
        }
    }
    public void rateTeacher(Teacher teacher, int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        teacherRatings.computeIfAbsent(teacher, k -> new ArrayList<>()).add(rating);
        System.out.println("Teacher " + teacher.getName() + " " + teacher.getSurname() + " rated: " + rating);
    }
        
        public void updateTranscript(Course course, double grade) {
            Mark mark = new Mark(course, this, grade);
            transcript.addMark(mark);
        
    
    }
}