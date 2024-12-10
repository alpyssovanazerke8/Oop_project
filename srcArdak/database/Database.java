package database;

import java.io.*;
import utils.Credentials;
import users.*;
import java.util.*;
import java.time.Year;
import enums.*;
import java.util.stream.Collectors;


public class Database implements Serializable {

    public static Database DATA;

    private HashMap<Credentials, User> users = new HashMap<Credentials, User>();

    private Vector<Researcher> researchers = new Vector<Researcher>();

    private Vector<String> logs = new Vector<String>();

    private Period period;
    private int year;

    static {
        File dataFile = new File("data");
        if (dataFile.exists()) {
            try {
                DATA = read(); // Attempt to read and deserialize the file
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception for debugging
            }
        } else {
            DATA = new Database(); // Initialize a new database if the file does not exist
        }
    }


    public HashMap<Credentials, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<Credentials, User> users) {
        this.users = users;
    }

    public Vector<Researcher> getResearchers() {
        return researchers;
    }

    public void setResearchers(Vector<Researcher> researchers) {
        this.researchers = researchers;
    }

    public Vector<String> getLogs() {
        return logs;
    }

    public void setLogs(Vector<String> logs) {
        this.logs = logs;
    }

    public Period getPeriod() {
        updateTime();
        return period;
    }
    public void setPeriod(Period period) {
        this.period = period;
    }

    public int getYear() {
        updateTime();
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public Vector<Teacher> getTeachers() {
        return users.values().stream().filter(n->n instanceof Teacher).map(n->(Teacher)n).collect(Collectors.toCollection(Vector<Teacher>::new));
    }

    public Vector<Student> getStudents() {
        return users.values().stream().filter(n->n instanceof Student).map(n->(Student)n).collect(Collectors.toCollection(Vector<Student>::new));
    }


    public void updateTime() {
        Date current = new Date();
        setYear(Year.now().getValue());
        setPeriod((current.getMonth()<5?Period.SPRING:(current.getMonth()<8)?Period.SUMMER:Period.FALL));
    }

    public static Database read() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("data");
        ObjectInputStream oin = new ObjectInputStream(fis);
        return (Database) oin.readObject();
    }

    public static void write() throws IOException{
        FileOutputStream fos = new FileOutputStream("data");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(DATA);
        oos.close();
    }
}
