package utils;

import enums.LessonType;
import users.Teacher;

import java.io.Serializable;
import java.time.LocalTime;

public class Lesson implements Serializable {
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private LessonType lessonType;
    private Teacher instructor;

    public Lesson(String day, LocalTime startTime, LocalTime endTime, Teacher instructor, LessonType lessonType){
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.instructor = instructor;
        this.lessonType = lessonType;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Teacher getInstructor() {
        return instructor;
    }

    public void setInstructor(Teacher instructor) {
        this.instructor = instructor;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public String toString() {
        return "Lesson{" +
                "day='" + day + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", instructor=" + instructor.getFirstName() + " " + instructor.getLastName() +
                ", lessonType=" + lessonType + '}';
    }

}
