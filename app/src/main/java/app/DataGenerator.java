package app;

import java.util.Random;

public class DataGenerator {

    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public String generateNewStudentUsername() {
        String newStudentBeginningUsername = "NewStudentAutotest";
        return newStudentBeginningUsername + getRandomNumber(1000, 9999);
    }

    public String generateNewTeacherUsername() {
        String newTeacherBeginningUsername = "NewTeacherAutotest";
        return newTeacherBeginningUsername + getRandomNumber(1000, 9999);
    }
    public String generateNewParentUsername() {
        String newParentBeginningUsername = "NewParentAutotest";
        return newParentBeginningUsername + getRandomNumber(1000, 9999);
    }

    public String generateNewPassword() {
        String newPasswordBeginningPassword = "autoTestPassword";
        return newPasswordBeginningPassword + getRandomNumber(1000, 9999);
    }
}
