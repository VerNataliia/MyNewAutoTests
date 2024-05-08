package common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class QuizDataExtractorEXCEL {
    private String correctAnswer;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public QuizDataExtractorEXCEL() {
        // Constructor logic if needed
    }

    public String extractAnswer(String titleQuiz, String titleQuestion) {
        try (FileInputStream fis = new FileInputStream("src/main/resources/files/QuizAnswers.xlsx");
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // The first sheet contains the quiz data
            for (Row row : sheet) {
                Cell titleCell = row.getCell(0); // Corrected index for "quiz_title"
                Cell questionCell = row.getCell(1); // Corrected index for "question"
                Cell answerCell = row.getCell(2); // Corrected index for "answer"

                if (titleCell != null && questionCell != null && answerCell != null) {
                    String titleCellValue = titleCell.getStringCellValue().trim();
                    String questionCellValue = questionCell.getStringCellValue().trim();
                    String answerCellValue = answerCell.getStringCellValue().trim();

                    if (titleCellValue.equals(titleQuiz) && questionCellValue.contains(titleQuestion)) {
                        correctAnswer = answerCellValue;

                        if (correctAnswer.startsWith("\"") && correctAnswer.endsWith("\"")) {
                            // Remove the double quotes
                            correctAnswer = correctAnswer.substring(1, correctAnswer.length() - 1);
                        }

                        System.out.println("Correct answer for " + questionCellValue + " is " + correctAnswer);
                        return correctAnswer;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Return null if no matching quiz and titleQuestion combination is found
    }
}
