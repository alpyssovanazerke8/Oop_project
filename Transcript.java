package oopprojectdraft;

import java.util.ArrayList;
import java.util.List;

public class Transcript {
    private Student student;
    private List<Mark> marks;

    public Transcript(Student student) {
        this.student = student;
        this.marks = new ArrayList<>();
    }

    public void addMark(Mark mark) {
        marks.add(mark);
    }

    public List<Mark> getMarks() {
        return new ArrayList<>(marks);
    }

    public double getGPA() {
        if (marks.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Mark mark : marks) {
            sum += mark.getValue();
        }
        return sum / marks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transcript for ").append(student.getFirstName()).append(" ").append(student.getSurname()).append("\n");
        sb.append("GPA: ").append(String.format("%.2f", getGPA())).append("\n");
        sb.append("Courses:\n");
        for (Mark mark : marks) {
            sb.append(mark.toString()).append("\n");
        }
        return sb.toString();
    }
}