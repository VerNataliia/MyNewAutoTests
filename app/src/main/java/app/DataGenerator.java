package app;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

    // Create a random unique for test class name
    public static class ClassNameGenerator {
        private final List<String> availableClassNames;

        public ClassNameGenerator() {
            // Initialize the list with your class names, then shuffle them to randomize the order
            availableClassNames = new ArrayList<>(Arrays.asList("Elementary Students", "Middle School", "High School",
                "Part-time students", "Grade 10", "Fourth Grade AG", "WRITING AND READING WORKSHOP", "Reading Lab", "English Tutoring",
                "2nd Period 2024", "3rd Grade Reading", "Practice class", "Language Lab", "Homeschool 2024", "Advanced Honors English", "Group B"));
            Collections.shuffle(availableClassNames);
        }

        public String getRandomClassName() {
            if (availableClassNames.isEmpty()) {
                throw new IllegalStateException("No more class names available");
            }
            // Remove and return the first element of the list, ensuring it's not repeated
            return availableClassNames.remove(0);
        }
    }

    //Get next day of week (for recurring weekly activities)
    public static String getDayOfWeek(int numberOfPlusDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, numberOfPlusDays);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        return simpleDateFormat.format(calendar.getTime());
    }
    //Get calendar day
    public static String getDatePlusDays(int days) {
        LocalDate futureDate = LocalDate.now().plusDays(days);
        return futureDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}