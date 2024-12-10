package utils;

import java.io.Serializable;
import java.time.*;
import java.util.*;

public class Schedule implements Serializable {
    private List<Lesson> lessons;

    public Schedule() {
        this.lessons = new ArrayList<>();
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public boolean addLesson(Lesson newLesson) {
        if(!newLesson.getInstructor().equals(this)) return false;
        for (Lesson lesson : lessons) {
            if (lesson.getDay().equals(newLesson.getDay()) &&
                    timesOverlap(lesson.getStartTime(), lesson.getEndTime(), newLesson.getStartTime(), newLesson.getEndTime())) {
                System.out.println("Schedule conflict! Cannot add the lesson: " + newLesson);
                return false;
            }
        }
        lessons.add(newLesson);
        System.out.println("Lesson added successfully: " + newLesson);
        return true;
    }

    private boolean timesOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return !(end1.isBefore(start2) || start1.isAfter(end2));
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "lessons=" + lessons +
                '}';
    }
}
