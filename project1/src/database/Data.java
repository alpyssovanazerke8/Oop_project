package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import enums.Period;
import enums.StudentType;
import users.Student;
import users.User;
import utils.Course;
import utils.Generator;
import utils.News;


public class Data implements Serializable {
	
	/*
	 User list
	 
	 */
	private Vector<String> logs = new Vector<String>();
	
	private Vector<News> news = new Vector<News>();
	
	private Vector<Course> courses = new Vector<Course>();
	
	private Vector<Student> students = new Vector<>();
	
	private HashMap<Generator, User> users = new HashMap<Generator, User>();
	
	private Period period;
    
    private int year;
	
	public static Data INSTANCE;
	static {
		if (new File("data").exists()) {
			try {
				INSTANCE = read();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else INSTANCE = new Data();
	}
	
	private Data() {
		
	}
	
	 public static Data read() throws IOException, ClassNotFoundException{
	    	FileInputStream fis = new FileInputStream("data");
			ObjectInputStream oin = new ObjectInputStream(fis);
			return (Data) oin.readObject();
	    }
	    
	    
	    public static void write() throws IOException{
	    	FileOutputStream fos = new FileOutputStream("data");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(INSTANCE);
			oos.close();
		}
	    
	    public Vector<String> getLogs(){
	    	return logs;
	    }
	    
	    public void setLogs(Vector<String> logs) {
			this.logs = logs;
		}
	    
	    public Vector<News> getNews() {
			return news;
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
		
		public void updateTime() {
			Date current = new Date();
	        setYear(Year.now().getValue());
	        setPeriod((current.getMonth()<5?Period.SPRING:(current.getMonth()<8)?Period.SUMMER:Period.FALL));
	    }
		
		public Vector<Course> getCourses() {
			return courses;
		}
		
		public void setCourses(Vector<Course> courses) {
			this.courses = courses;
		}
		
		public HashMap<Generator, User> getUsers() {
			return users;
		}
		
		public void setUsers(HashMap<Generator, User> users) {
			this.users = users;
		}
}
