package users;

import database.Database;
import enums.*;

import java.io.IOException;

public class Researcher extends Employee{
    private School school;

    public Researcher(){
        super();
    }

    public Researcher(String firstName, String lastName, School school){
        super(firstName, lastName);
        this.school = school;
        Database.DATA.getResearchers().add(this);
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public void run() throws IOException {

    }
}
