package users;

public class Teacher extends Employee{
   /* private DegreeType degree;
    private School school;
    private Vector<Integer> ratings;
    private Schedule schedule;

    {
        this.setId((new Date()).getYear() + "T" + Data.INSTANCE.getTeachers().size());
    }

    public Teacher(){
        super();
    }

    public Teacher(String firstName, String lastName, School school, DegreeType degree){
        super(firstName,lastName);
        this.school = school;
        this.degree = degree;

    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public DegreeType getDegreeType() {
        return degree;
    }

    public void setDegreeType(DegreeType degree) {
        this.degree = degree;
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

    public Vector<Course> viewCourses() {
        Vector<Course> myCourses = new Vector<>();

        for (Course course : Data.INSTANCE.getCourses()) {
            if (course.getYear() == Data.INSTANCE.getYear() && course.getPeriod() == Data.INSTANCE.getPeriod()) {
                for (Lesson lesson : course.getLessons()) {
                    if (lesson.getInstructor().equals(this) && !myCourses.contains(course)) myCourses.add(course);
                }
            }
        }
        return myCourses; 
    }

    public Vector<String> getCoursesDescriptions() {
        Vector<Course> courses = this.viewCourses();
        return courses.stream()
                .map(Course::toString)
                .collect(Collectors.toCollection(Vector::new));
    }

    public Vector<Student> viewStudents() {
        Vector<Student> myStudents = new Vector<>();
        for (Course course : viewCourses()) {
            for (Student student : course.getStudentMark().keySet()) {
                if (!myStudents.contains(student)) {
                    myStudents.add(student);
                }
            }
        }
        return myStudents;
    }

    public Vector<String> getStudentsDescriptions() {
        Vector<Student> students = this.viewStudents();
        return students.stream()
                .map(Student::toString)
                .collect(Collectors.toCollection(Vector::new));
    }
    
    public Optional<Student> findStudentByIndex(int index) {
        Vector<Student> students = this.viewStudents();
        if (index < 0  index >= students.size()) {
            return Optional.empty();
        }
        return Optional.of(students.get(index));
    }

    public boolean putMark(Student student, Course course, double mark, GradeType gradeType) {
        if (!course.getEnrolledStudents().contains(student)) {
            return false;
        }

        Mark marks = course.getStudentMark().get(student);
        if (marks == null) {
            marks = new Mark();
            course.getStudentMark().put(student, marks);
        }

        switch (gradeType) {
            case FIRST_ATT -> marks.setFirstAttestation(mark);
            case SECOND_ATT -> marks.setSecondAttestation(mark);
            case FINAL -> marks.setFinalExam(mark);
            default -> throw new IllegalArgumentException("Invalid grade type.");
        }

        return true;
    }

    public Complaint createComplaint(String content, Student student, Urgency urgency) {
        return new Complaint(content, this, urgency); // Return a Complaint object
    }

    public Optional<Manager> findResponsibleManager(Student student) {
        School studentSchool = student.getSchool();
        int joiningYear = student.getStartYear();
        int currentYear = Year.now().getValue();

        ManagerType managerType;
        if (joiningYear == currentYear) {
            managerType = ManagerType.DEAN_1ST;
        } else if (joiningYear == currentYear - 1) {
            managerType = ManagerType.DEAN_2ND;
        } else if (joiningYear == currentYear - 2) {
            managerType = ManagerType.DEAN_3RD;
        } else {
            managerType = ManagerType.DEAN_4TH;
        }

        return Data.INSTANCE.getManagers().stream()
                .filter(m -> m.getManagerType() == managerType)
                .filter(m -> m.getSchool().equals(studentSchool))
                .findFirst();
    }

methods as find Responsible manager and create complaint will be used in the menu together when sending the complaint, 
    i thought that the sendComplaint method will be too big, so i divided it inot two

    public double getTeacherRating() {
        double sum = ratings.stream().mapToDouble(Integer::doubleValue).sum();
        return ratings.isEmpty() ? 0.0 : sum / ratings.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null getClass()!= obj.getClass()) return false;

        Teacher other = (Teacher) obj;

        if (degree != other.degree) return false;
        if (school != null ? !school.equals(other.school) : other.school != null) return false;
        if (ratings != null ? !ratings.equals(other.ratings) : other.ratings != null) return false;
        return schedule != null ? !schedule.equals(other.schedule) : other.schedule != null;
    }
}
*/
}
