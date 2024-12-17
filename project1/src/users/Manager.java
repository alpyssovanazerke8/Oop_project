package users;
import java.util.HashMap;
import java.util.Vector;

import database.Data;
import enums.ManagerType;
import utils.Course;
import utils.CourseBase;
import utils.News;
import utils.Request;
import utils.Lesson;

public class Manager extends Employee {
	/*private Vector<Request> requests = new Vector<Request>;
	*/
	
	private ManagerType managerType;
	
	public Manager() {
		super();
		this.managerType = ManagerType.OR;
	}
	
	
	/*Constructor with exception*/
	public Manager(String firstName, String lastName, ManagerType managerType){
		super(firstName, lastName);
		if (managerType == null) {
            throw new IllegalArgumentException("Manager type cannot be null");
        }
        this.managerType = managerType;
        
    }
	
	public ManagerType getManagerType() {
		return managerType;
	}
	
	public void setManagerType(ManagerType managerType) {
		this.managerType = managerType;
	}
	
	/*
	 public Vector<Request> getRequests() {
		return requests;
	}
	 * */
	
	 public void addNews(News news) {
    	Data.INSTANCE.getNews().add(news);
    }
	 
	 public String getReport(Course course) {
	    	String report =  "Statistical report for course: " + course.getCourseName()+"\n" + course.displayReport();
	        return report;
	    }
	 
	 public void addCourses(HashMap<Course, Integer> subjects) {
		    for (Course subject : subjects.keySet()) {
		        int courseLimit = subjects.get(subject);

		        // Создаём новый объект Course, копируя данные из существующего `subject`
		        Course newCourse = new Course(
		            subject.getCode(),
		            subject.getCourseName(),
		            subject.getCredits(),
		            subject.getCourseType(), // Предполагаем, что courseType уже готова как HashMap<School, CourseType>
		            courseLimit
		        );

		        // Добавляем новый курс в базу данных
		        Data.INSTANCE.getCourses().add(newCourse);
		    }
		}

	 
	 public void assignCourseToTeachers(Course course, Vector<Lesson> lessons) {
		   	course.setLessons(lessons);
		}
}
