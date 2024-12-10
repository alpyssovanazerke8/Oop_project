package users;

import enums.*;

import java.io.*;
import java.util.*;
import database.Database;
import uniSystem.*;
import utils.*;
import java.time.Year;
import java.util.stream.Collectors;


import static enums.GradeType.*;

public class Teacher extends Employee implements CanBecomeResearcher, CanViewCourses, CanViewStudents{
    private DegreeType degreeType;
    private School school;
    private Vector<Integer> ratings;
    private Schedule schedule = new Schedule();


    private Researcher researchingInfo;

    {
        this.setId((new Date()).getYear() + "T" + Database.DATA.getTeachers().size());
    }

    public Teacher(){
        super();
    }

    public Teacher(String firstName, String lastName, School school, DegreeType degree){
        super(firstName,lastName);
        this.school = school;
        this.degreeType = degreeType;
        if(degreeType == DegreeType.PHD){
            becomeResearcher();
        }
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public DegreeType getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
    }

    public Researcher getResearchingInfo() {
        return researchingInfo;
    }

    public void setResearchingInfo(Researcher researchingInfo) {
        this.researchingInfo = researchingInfo;
    }

    public Vector<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(Vector<Integer> rating) {
        this.ratings = rating;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }



    public void putMarks() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Vector<Course> courses = this.viewCourses();
        if (courses.isEmpty()) {
            System.out.println("You don't have any available courses.");
            return;
        }

        System.out.println("Select a course (#):");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i));
        }
        int courseIndex = Integer.parseInt(br.readLine()) - 1;
        if (courseIndex < 0 || courseIndex >= courses.size()) {
            System.out.println("Invalid choice. Returning to the menu.");
            return;
        }
        Course selectedCourse = courses.get(courseIndex);

        Vector<Student> students = selectedCourse.getEnrolledStudents();
        if (students.isEmpty()) {
            System.out.println("No students are enrolled in this course.");
            return;
        }

        System.out.println("Select a student (#):");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
        int studentIndex = Integer.parseInt(br.readLine()) - 1;
        if (studentIndex < 0 || studentIndex >= students.size()) {
            System.out.println("Invalid choice. Returning to the menu.");
            return;
        }
        Student selectedStudent = students.get(studentIndex);

        boolean teacherTeachesThisStudent = selectedCourse.getLessons().stream()
                .anyMatch(lesson -> lesson.getInstructor().equals(this)
                        && selectedCourse.getEnrolledStudents().contains(selectedStudent));

        if (!teacherTeachesThisStudent) {
            System.out.println("You are not authorized to grade this student as you are not their teacher.");
            return;
        }

        System.out.println("Enter the new mark:");
        double newMark;
        try {
            newMark = Double.parseDouble(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid mark input. Returning to the menu.");
            return;
        }

        System.out.println("Select mark type:");
        System.out.println("1. FIRST_ATT");
        System.out.println("2. SECOND_ATT");
        System.out.println("3. FINAL_EXAM");

        int markTypeIndex;
        try {
            markTypeIndex = Integer.parseInt(br.readLine()) - 1;
            if (markTypeIndex < 0 || markTypeIndex >= GradeType.values().length) {
                System.out.println("Invalid choice. Returning to the menu.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice. Returning to the menu.");
            return;
        }
        GradeType markType = GradeType.values()[markTypeIndex];

        Mark mark = new Mark();
        switch (markType) {
            case FIRST_ATT -> mark.setFirstAttestation(newMark);
            case SECOND_ATT -> mark.setSecondAttestation(newMark);
            case FINAL -> mark.setFinalExam(newMark);
            default -> {
                System.out.println("Invalid mark type. Returning to the menu.");
                return;
            }
        }

        System.out.println("Mark successfully updated for student: " + selectedStudent.getId());
    }


    public void sendComplaint() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Vector<Student> students = this.viewStudents();
            if (students.isEmpty()) {
                System.out.println("No available students.");
                return;
            }

            System.out.println("Who is the complaint about (#)?");
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ". " + students.get(i));
            }

            int studentIndex = Integer.parseInt(br.readLine()) - 1;
            if (studentIndex < 0 || studentIndex >= students.size()) {
                System.out.println("Invalid choice. Returning to the menu.");
                return;
            }
            Student selectedStudent = students.elementAt(studentIndex);

            System.out.println("Type your complaint:");
            String content = br.readLine();

            System.out.println("Select urgency level:");
            System.out.println("1. HIGH");
            System.out.println("2. MEDIUM");
            System.out.println("3. LOW");

            int urgencyLevel;
            try {
                urgencyLevel = Integer.parseInt(br.readLine());
                if (urgencyLevel < 1 || urgencyLevel > Urgency.values().length) {
                    System.out.println("Invalid choice. Returning to the menu.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Returning to the menu.");
                return;
            }
            Urgency urgency = Urgency.values()[urgencyLevel - 1];

            Complaint complaint = new Complaint(content, this, urgency);

            School studentSchool = selectedStudent.getSchool();
            int joiningYear = selectedStudent.getStartYear();
            int currentYear = Year.now().getValue();

            ManagerType managerType;
            if (joiningYear == currentYear) {
                managerType = ManagerType.FIRST_DEAN;
            } else if (joiningYear == currentYear - 1) {
                managerType = ManagerType.SECOND_DEAN;
            } else if (joiningYear == currentYear - 2) {
                managerType = ManagerType.THIRD_DEAN;
            } else {
                managerType = ManagerType.FOURTH_DEAN;
            }

            Optional<Manager> manager = Database.DATA.getManagers().stream()
                    .filter(m -> m.getManagerType() == managerType)
                    .filter(m -> m.getSchool().equals(studentSchool))
                    .findFirst();

            if (manager.isPresent()) {
                manager.get().getNotifications().add(complaint);
                System.out.println("Complaint submitted successfully.");
            } else {
                System.out.println("No suitable manager found for this complaint.");
            }
    }


    public double getTeacherRating() {
        double sum = 0.0;
        for (int rate : ratings) {
            sum += rate;
        }
        return ratings.isEmpty() ? 0.0 : sum / ratings.size();
    }

    public Vector<Student> viewStudents() {
        Vector<Student> myStudents = new Vector<Student>();
        for (Course course : viewCourses()) {
            for (Student student : course.getGradebook().keySet()) {
                if (myStudents.contains(student)) {
                    continue;
                }
                myStudents.add(student);
            }
        }
        return myStudents;
    }

    public void printStudents(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Vector<Student> students = this.viewStudents();

        if (students.isEmpty()) {
            System.out.println("You don't have any students.");
            return;
        }

        System.out.println("List of Students:");
        System.out.println(students.stream()
                .map(Student::toString)
                .collect(Collectors.joining("\n")));
    }

    public Vector<Course> viewCourses() {
        Vector<Course> myCourses = new Vector<Course>();

        for (Course course : Database.DATA.getCourses()) {
            if(course.getYear()==Database.DATA.getYear() && course.getPeriod()==Database.DATA.getPeriod()) {
                for (Lesson lesson : course.getLessons()) {
                    if (lesson.getInstructor().equals(this) && !myCourses.contains(course)) myCourses.add(course);
                }
            }
        }
        return myCourses;
    }

     public void printCourses(){
         Vector<Course> courses = this.viewCourses();

         if (courses.isEmpty()) {
             System.out.println("You don't have any courses yet!");
             return;
         }

         System.out.println("Your Courses:");
         System.out.println(courses.stream()
                 .map(Course::toString)
                 .collect(Collectors.joining("\n")));
     }

    public void becomeResearcher(){
        this.researchingInfo = (Researcher) new UserFactory().getUser(this);
    }

    @Override
    public void unbecomeResearcher() {
        Database.DATA.getResearchers().remove(researchingInfo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Teacher other = (Teacher) obj;

        if (degreeType != other.degreeType) return false;
        if (school != null ? !school.equals(other.school) : other.school != null) return false;
        if (ratings != null ? !ratings.equals(other.ratings) : other.ratings != null) return false;
        if (schedule != null ? !schedule.equals(other.schedule) : other.schedule != null) return false;

        return researchingInfo != null ? researchingInfo.equals(other.researchingInfo) : other.researchingInfo == null;
    }


    public void run() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            menu:while(true) {
                System.out.println("What do you want to do?\n" +
                        "1) Read Notifictions\n" +
                        "2) Send message\n" +
                        "3) Send Complaint\n" +
                        "4) Put Marks\n" +
                        "5) Get Rating\n" +
                        "6) View Students\n" +
                        "7) View Courses\n"
                        "7) Exit\n" );
                int choice = Integer.parseInt(br.readLine());
                switch (choice) {
                    case 1 -> readNotifications();
                    case 2 -> sendMessage();
                    case 3 -> sendComplaint();
                    case 4 -> putMarks();
                    case 5 -> System.out.println("Teacher rating: " + getTeacherRating());
                    case 6 -> printStudents();
                    case 7 -> printCourses();
                    case 8 -> {
                        exit();
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }

            }

        }catch(Exception e) {
            System.out.println("Saving resources");
            e.printStackTrace();
            save();
        }
    }
}
