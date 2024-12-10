package main;

import database.Database;
import enums.DegreeType;
import enums.School;
import uniSystem.UniSystem;
import users.Teacher;
import utils.Credentials;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException{
          Database.DATA.getUsers().put(new Credentials("a_abdu@kbtu.kz", "12345"), new Teacher("ardak", "abdullakhan", School.SITE, DegreeType.PHD));
          Database.DATA.getUsers().put(new Credentials("a_abdull@kbtu.kz", "123456"), new Teacher("ardak", "abdulla", School.SITE, DegreeType.PHD));

//        new UniSystem().run();
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            try{
//                Database.write();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            System.out.println("Shutdown hook executed");
//        }));

    }
}
