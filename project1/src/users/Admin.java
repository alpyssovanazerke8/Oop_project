package users;

import database.Data;
import enums.StudentType;
import enums.ManagerType;
import utils.Generator;

public class Admin extends Employee {

    public Admin() {
        super();
    }

    public Admin(String firstName, String lastName) {
        super(firstName, lastName);
    }

    /**
     * Добавляет существующего пользователя в систему.
     *
     * @param user Пользователь, которого нужно добавить.
     */
    public void addUser(User user) {
        String userType = determineUserType(user);
        int year = Data.INSTANCE.getYear(); // Текущий год
        String id = Generator.generateId(userType, 
                                          user instanceof Student ? ((Student) user).getStudentType() : null, 
                                          year);
        String password = Generator.generatePassword(8); // Генерация пароля длиной 8 символов

        System.out.println("Generated ID: " + id);
        System.out.println("Generated Password: " + password);
        System.out.println("DO NOT SHARE! Save this information for later use.");

        // Дополнительные действия по добавлению пользователя в систему
        if (user instanceof Student) {
            ((Student) user).setStudentId(id);
            Data.INSTANCE.getStudents().add((Student) user);
        }
        
        Generator newGenerator = new Generator(id, password);
       
        Data.INSTANCE.getUsers().put(newGenerator, user);
    }

    /**
     * Создаёт и добавляет нового студента в систему.
     *
     * @param firstName   Имя.
     * @param lastName    Фамилия.
     * @param studentType Тип студента (BACHELOR, MASTER, PHD).
     */
    public void addUser(String firstName, String lastName, StudentType studentType) {
        Student student = new Student(firstName, lastName);
        student.setStudentType(studentType);
        int enrolledYear = Data.INSTANCE.getYear();
        student.setEnrolledYear(enrolledYear);

        addUser(student); // Вызов метода для добавления пользователя
    }

    /**
     * Создаёт и добавляет нового учителя в систему.
     *
     * @param firstName Имя.
     * @param lastName  Фамилия.
     */
  /*  public void addUser(String firstName, String lastName) {
        Teacher teacher = new Teacher(firstName, lastName);
        addUser(teacher); // Вызов метода для добавления пользователя
    }*/

    /**
     * Создаёт и добавляет нового менеджера в систему.
     *
     * @param firstName   Имя.
     * @param lastName    Фамилия.
     * @param managerType Тип менеджера.
     */
    public void addUser(String firstName, String lastName, ManagerType managerType) {
        Manager manager = new Manager(firstName, lastName, managerType);
        addUser(manager); // Вызов метода для добавления пользователя
    }

    /**
     * Удаляет пользователя из системы по ID.
     *
     * @param id Уникальный ID пользователя.
     */
    public void removeUser(String id) {
        Data.INSTANCE.getUsers().keySet().removeIf(user -> Data.INSTANCE.getUsers().get(user).equals(id));
        System.out.println("User with ID " + id + " has been removed.");
    }

    /**
     * Определяет тип пользователя по классу.
     *
     * @param user Пользователь.
     * @return Тип пользователя как строка (STUDENT, TEACHER, MANAGER, ADMIN).
     */
    private String determineUserType(User user) {
        if (user instanceof Student) return "STUDENT";
        if (user instanceof Teacher) return "TEACHER";
        if (user instanceof Manager) return "MANAGER";
        if (user instanceof Admin) return "ADMIN";
        throw new IllegalArgumentException("Unknown user type.");
    }
}

/*
 public static String generateStudentId(int year, StudentType type) {
        char typeChar = type == StudentType.BACHELOR ? 'B' : type == StudentType.MASTER ? 'M' : 'P';
        return String.format("%d%c%06d", year, typeChar, studentCounter++);
    }
 * */
 