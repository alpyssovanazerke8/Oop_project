package users;

import utils.Post;

import java.io.IOException;
import java.util.*;
import database.Database;
import java.io.Serializable;
import java.time.Year;
import java.util.stream.Collectors;

public abstract class User implements Serializable {
    private String firstName;
    private String lastName;
    private String id;
    private Year joinYear;

    private Vector<Post> notifications = new Vector<Post>();

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public Vector<Post> getNotifications() {
        return notifications;
    }

    public void setNotifications(Vector<Post> notifications) {
        this.notifications = notifications;
    }

    public void readNotifications(){
        notifications.sort(null);
        if(notifications.size() > 0){
            System.out.println(notifications.stream().map(n->n.toString()).collect(Collectors.joining("\n")));
        }else{
            System.out.println("No incoming Notifications");
        }
    }


    public abstract void run() throws IOException;

    public void save() throws IOException {
        try {
            Database.write();
            System.out.println("Resources saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save resources.");
            e.printStackTrace();
        }
    }

    public void exit() {
        System.out.println("You've logged out succesfully");
        Database.DATA.getLogs().add(this + " logged out system at " + new Date());
        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
