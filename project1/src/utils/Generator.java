package utils;

import enums.StudentType;
import java.security.SecureRandom;
import java.time.Year;
import java.util.concurrent.atomic.AtomicInteger;

public class Generator {
	
    private static final AtomicInteger counter = new AtomicInteger(1); // Уникальный счетчик для генерации ID
    private static final SecureRandom random = new SecureRandom(); // Генератор для случайных паролей
    private static final String PASSWORD_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
    
    private String id;
    private String password;
    
    public Generator() {
    	
    }
    
    public Generator(String id, String password) {
    	this.id = id;
    	this.password = password;
    }
    /**
     * Генерация ID для пользователей.
     *
     * @param userType   Тип пользователя (например, "STUDENT", "TEACHER", "MANAGER", "ADMIN").
     * @param studentType Тип студента, если пользователь - студент (иначе null).
     * @param year       Год поступления/найма.
     * @return Уникальный ID.
     */
    
    
    public static String generateId(String userType, StudentType studentType, int year) {
        String prefix = "";
        switch (userType.toUpperCase()) {
            case "STUDENT":
                if (studentType == null) throw new IllegalArgumentException("StudentType cannot be null for a student.");
                prefix = switch (studentType) {
                    case BACHELOR -> "B";
                    case MASTER -> "M";
                    case PHD -> "P";
                };
                break;
            case "TEACHER":
                prefix = "T";
                break;
            case "MANAGER":
                prefix = "ME";
                break;
            case "ADMIN":
                prefix = "A";
                break;
            default:
                throw new IllegalArgumentException("Invalid user type.");
        }

        // Формат: Год + Префикс + Уникальный номер
        return String.format("%d%s%06d", year, prefix, counter.getAndIncrement());
    }

    /**
     * Генерация случайного пароля.
     *
     * @param length Длина пароля.
     * @return Случайный пароль.
     */
    public static String generatePassword(int length) {
        if (length < 6) throw new IllegalArgumentException("Password length must be at least 6 characters.");
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(PASSWORD_CHARACTERS.charAt(random.nextInt(PASSWORD_CHARACTERS.length())));
        }
        return password.toString();
    }
}

