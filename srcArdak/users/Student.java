package users;

import database.Database;
import enums.School;

import java.io.IOException;
import java.io.Serializable;

public class Student extends User implements Serializable {
    private School school;
    private Researcher researchinginfo;
    private int startYear;

    {
        this.startYear= Database.DATA.getYear();
        this.setId(startYear+"B"+Database.DATA.getStudents().size());
        //Database.DATA.getStudents().add(this);
    }

    public Student() {

    }
    public Student(String firstName, String lastName, School school) {
        super(firstName, lastName);
        this.school = school;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Researcher getResearchinginfo() {
        return researchinginfo;
    }

    public void setResearchinginfo(Researcher researchinginfo) {
        this.researchinginfo = researchinginfo;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @Override
    public void run() throws IOException {

    }
}
