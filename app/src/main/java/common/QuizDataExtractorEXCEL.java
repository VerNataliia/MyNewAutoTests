package common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class QuizDataExtractorEXCEL {
    private String correctAnswer;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public QuizDataExtractorEXCEL() {
        Set<String> distinctTrios = new LinkedHashSet<>();
    }

    public String extractAnswer(String titleQuiz, String titleQuestion) {
        try (FileInputStream fis = new FileInputStream("/Users/nataliiaverba/readtheoty/app/src/main/resources/files/QuizAnswers.xlsx");
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // The first sheet contains the quiz data
            for (Row row : sheet) {
                Cell titleCell = row.getCell(0); // The "reading_comprehension_quiz_title" column has an index 1
                Cell questionCell = row.getCell(1); // The "question" column has an index 2
                Cell answerCell = row.getCell(2); // The "answer" column has an index 3

                String titleCellValue = titleCell.getStringCellValue().trim();
                String questionCellValue = questionCell.getStringCellValue().trim();
                String answerCellValue = answerCell.getStringCellValue().trim();

                if (titleCellValue.equals(titleQuiz) && questionCellValue.contains(titleQuestion)) {
                    correctAnswer = answerCell.getStringCellValue();

                    if (correctAnswer.startsWith("\"") && correctAnswer.endsWith("\"")) {
                        // Remove the double quotes
                        correctAnswer = correctAnswer.substring(1, correctAnswer.length() - 1);
                    }

                    System.out.println("Correct answer for " + questionCellValue + " is " + correctAnswer);
                    return correctAnswer;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Return null if no matching quiz and titleQuestion combination is found
    }
}



