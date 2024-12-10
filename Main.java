package oopprojectdraft;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        School school = School.SITE;

        Teacher mathTeacher = new Teacher("John", "Doe");
        Teacher csTeacher = new Teacher("Jane", "Smith");

        Course mathCourse = new Course("Calculus", "MATH101", mathTeacher);
        Course csCourse = new Course("Programming Basics", "CS101", csTeacher);
        
        Organization ositEntertainment = new Organization("OSIT Entertainment");
        Organization artHouse = new Organization("Art House");
        


        school.addCourse(mathCourse);
        school.addCourse(csCourse);

        Student student = new Student("Nura", "Nurlan", 23031300 , school);

        List<Course> availableCourses=student.viewCourses();
        System.out.println("\n");
        student.registerForCourses(availableCourses);

        student.viewTeacherInfo(mathCourse);
        System.out.println("\n");

        mathCourse.setMark(student, 85.0);
        csCourse.setMark(student, 92.0);

        student.viewMarks();
        System.out.println("\n");

        student.updateTranscript(mathCourse, 85.0);
        student.updateTranscript(csCourse, 92.0);
        System.out.println("\n");

        System.out.println(student.getTranscript()+"\n");

        try {
            student.takeRetake(mathCourse);
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\n");


        student.rateTeacher(mathTeacher, 5);
        student.rateTeacher(csTeacher, 4);
        System.out.println("\n");
        
        student.joinOrganization(ositEntertainment, "Member");
        student.joinOrganization(artHouse, "Head");
        System.out.println("\n");
        
        student.viewOrganizations();
        System.out.println("\n");

    }
}