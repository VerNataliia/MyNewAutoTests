package app;

import java.util.Random;

public class DataGenerator {
    private final Random random = new Random();
//Create a random number
    public int getRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

// Create a random first and last names
    private static final String[] FIRST_NAMES = {
        "James", "Mary", "John", "Patricia", "Robert", "Jennifer",
        "Michael", "Linda", "William", "Elizabeth", "David", "Barbara",
        "Richard", "Susan", "Joseph", "Jessica", "Thomas", "Sarah",
        "Charles", "Karen", "Christopher", "Nancy", "Daniel", "Lisa",
        "Matthew", "Margaret", "Anthony", "Betty", "Mark", "Sandra",
        "Donald", "Ashley", "Steven", "Kimberly", "Paul", "Emily",
        "Andrew", "Donna", "Joshua", "Michelle", "Kevin", "Carol",
        "Brian", "Amanda", "George", "Melissa", "Edward", "Deborah"
};

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia",
            "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez",
            "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore",
            "Jackson", "Martin", "Lee", "Perez", "Thompson", "White",
            "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson",
            "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres",
            "Nguyen", "Hill", "Flores", "Green", "Adams", "Nelson", "Baker",
            "Hall", "Rivera", "Campbell", "Mitchell", "Carter", "Roberts"
    };

    public String getRandomFirstName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }

    public String getRandomLastName() {
        return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }

// Create a random class name
private static final String[] CLASSES_NAME = {
        "Elementary Students", "Middle School", "High School", "Part-time students", "Grade 10",
        "Fourth Grade AG", "WRITING AND READING WORKSHOP", "Reading Lab", "English Tutoring", "2nd Period 2024",
        "3rd Grade Reading", "Practice class", "Language Lab", "Homeschool 2024", "Advanced Honors English", "Group B"
    };

    public String getRandomClassName() {
        return CLASSES_NAME[random.nextInt(CLASSES_NAME.length)];
    }
}