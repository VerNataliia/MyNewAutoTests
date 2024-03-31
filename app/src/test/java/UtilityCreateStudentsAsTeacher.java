import app.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilityCreateStudentsAsTeacher extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityCreateStudentsAsTeacher.class);

    public static List<Map<String, String>> createNewStudents(App app, int numberOfStudents, boolean useNames) {
        logger.info("Starting to create {} new students", numberOfStudents);
        app.classPage.clickOnAddNewStudentsButton();

        List<Map<String, String>> studentCredentialsList = new ArrayList<>();
        for (int i = 0; i < numberOfStudents; i++) {
            if (useNames) {
                app.addNewStudentsPage.addRandomFirstName();
                app.addNewStudentsPage.addRandomLastName();
            }
            String studentUsername = useNames ? app.addNewStudentsPage.addRandomStudentUsernameFromName()
                : app.addNewStudentsPage.addRandomStudentUsername();
            String studentPassword = app.addNewStudentsPage.addRandomStudentPassword();

            Map<String, String> studentCredentials = new HashMap<>();
            studentCredentials.put("username", studentUsername);
            studentCredentials.put("password", studentPassword);
            studentCredentialsList.add(studentCredentials);

            logger.debug("Added new student with username: {} and password: {}", studentUsername, studentPassword);
            app.addNewStudentsPage.clickOnAddNewStudentButton();
        }

        app.addNewStudentsPage.saveAddedStudents();
        logger.info("Saved {} new students", numberOfStudents);

        List<String> usernames = new ArrayList<>();
        for (Map<String, String> studentCredentials : studentCredentialsList) {
            usernames.add("(" + studentCredentials.get("username") + ")");
        }

        app.classPage.checkStudentsInClass(usernames);
        logger.info("Checked new students in class");
        return studentCredentialsList;
    }

    public static void saveCredentialsToExcel(List<Map<String, String>> credentialsList) {
        String[] columns = {"Username", "Password"};
        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Student Credentials");

        // Creating header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Filling data
        int rowNum = 1;
        for (Map<String, String> credentials : credentialsList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(credentials.get("username"));
            row.createCell(1).setCellValue(credentials.get("password"));
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        String filePath = "/Users/nataliiaverba/readtheoty/app/src/main/resources/files/StudentCredentials.xlsx";

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Saved Excel file to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
