package users;

public class UserFactory {
    public User getUser(Teacher teacher){
        return new Researcher(teacher.getFirstName(), teacher.getLastName(), teacher.getSchool());
    }
}
