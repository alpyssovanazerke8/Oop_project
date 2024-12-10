package utils;

import users.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Post implements Serializable, Comparable<Post>{

    private Date date;
    private String text;
    private User author;

    public Post(){
        this.date = new Date();
    }

    public Post(String text, User author){
        this();
        this.text = text;
        this.author = author;
    }

    public String toString(){
        return "🐱‍💻: " + (author==null?"SYSTEM": author) + "    [" + date.toLocaleString() + "]" + "\n\t" + text;
    }

    @Override
    public int compareTo(Post o) {
        return -1 * this.date.compareTo(o.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author,text,date);
    }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass());
        Post p = (Post) obj;
        return author.equals(p.author) && text.equals(p.text);
    }
}
