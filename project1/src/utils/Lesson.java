package utils;

import enums.LessonType;
import users.Teacher;

public class Lesson {
private LessonType lessonType;
    
    
    private Teacher instructor;

	public Lesson() {
		super();
	}
	public Lesson(LessonType lessonType, Teacher instructor) {
		this.lessonType=lessonType;
		this.instructor=instructor;
	}

	public LessonType getLessonType() {
		return lessonType;
	}

	public void setLessonType(LessonType lessonType) {
		this.lessonType = lessonType;
	}

	public Teacher getInstructor() {
		return instructor;
	}

	public void setInstructor(Teacher instructor) {
		this.instructor = instructor;
	}
}
