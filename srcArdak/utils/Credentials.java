package utils;

import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.*;

public class Credentials implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public Credentials(){

    }

    public Credentials(String username, String password){
        this.username = username;
        this.password = hashPassword(password);
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public static String hashPassword(String s) {
        int p = 31;
        int m = 1000000007;
        long hashValue = 0;
        long pPow = 1;

        for (char c : s.toCharArray()) {
            hashValue = (hashValue + (c - 'a' + 1) * pPow) % m;
            pPow = (pPow * p) % m;
        }

        return String.valueOf(hashValue);
    }


    public static String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        int length = 12;
        Random random = new Random();
        String password = "";

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password+=characters.charAt(index);
        }

        return password.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Credentials other = (Credentials) obj;
        return Objects.equals(password, other.password) && Objects.equals(username, other.username);
    }

    public int hashCode() {
        return Objects.hash(username + password);
    }

    public String toString() {
        return "["+username+",*****]";
    }
}
