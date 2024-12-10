package users;

import database.Database;
import utils.Post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.stream.Collectors;

public abstract class Employee  extends User{

    public Employee() {
        super();
    }


    public Employee(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public void sendMessage() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Vector<Employee> employees = Database.DATA.getUsers().values().stream()
                .filter(n->(n instanceof Employee)).map(n->(Employee)n).collect(Collectors.toCollection(Vector<Employee>::new));

        System.out.println("Select employee:");

        for(int i = 0; i < employees.size(); i++) {
            if(employees.get(i).equals(this)) {continue;}
            System.out.println(i+1 + " " + employees.get(i));
        }
        int empId = Integer.parseInt(br.readLine());
        System.out.println("Enter you message:");
        String message = br.readLine();
        employees.get(empId-1).getNotifications().add(new Post(message, this));
        System.out.println("Message was sent.");
    }
}
