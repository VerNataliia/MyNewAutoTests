package app.helpers;

import java.util.regex.Pattern;

public class Trim {
    public static String trim(String string, String trimSymbol) {
        trimSymbol = Pattern.quote(trimSymbol);
        String trimmed = ltrim(string, trimSymbol);
        return rtrim(trimmed, trimSymbol);
    }

    public static String ltrim(String string, String trimSymbol) {
        trimSymbol = Pattern.quote(trimSymbol);
        return string.replaceAll("^" + trimSymbol + "+", "");
    }

    public static String rtrim(String string, String trimSymbol) {
        trimSymbol = Pattern.quote(trimSymbol);
        return string.replaceAll(trimSymbol + "+$", "");
    }


    public static String getQuizNameFromActivityTitle(String activityTitle) {

        int startIndex = activityTitle.indexOf(" - ");
        String quizTitle = null;
        if (startIndex != -1) {

            quizTitle = activityTitle.substring(startIndex + 3);
            if (quizTitle.startsWith("\"")) {
                quizTitle = quizTitle.substring(1);
            }
            if (quizTitle.endsWith("\"")) {
                quizTitle = quizTitle.substring(0, quizTitle.length() - 1);
            }
        }
        return quizTitle;
    }
}
