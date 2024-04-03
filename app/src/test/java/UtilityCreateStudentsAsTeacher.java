import app.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public static void saveCredentialsToExcel(List<Map<String, String>> studentCredential, String[] teacherCredential) {
        String[] columns = {"Username", "Password"};
        Workbook workbook = new XSSFWorkbook();

        // Create and fill Student Credentials sheet
        Sheet studentSheet = workbook.createSheet("Student Credentials");
        fillSheet(studentSheet, columns, studentCredential, null);

        // Create and fill Teacher Credentials sheet
        Sheet teacherSheet = workbook.createSheet("Teacher Credentials");
        fillSheet(teacherSheet, columns, null, teacherCredential);

        // Write the workbook to a file
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filePath = "src/main/resources/files/Credentials_" + formattedDate + ".xlsx";

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Saved Excel file to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillSheet(Sheet sheet, String[] columns, List<Map<String, String>> studentCredential, String[] teacherCredential) {
        // Creating header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        if (studentCredential != null) {
            // Filling student data
            int rowNum = 1;
            for (Map<String, String> credentials : studentCredential) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(credentials.get("username"));
                row.createCell(1).setCellValue(credentials.get("password"));
            }
        } else if (teacherCredential != null) {
            // Filling teacher data
            Row row = sheet.createRow(1); // Assuming only one row for teacher credentials
            row.createCell(0).setCellValue(teacherCredential[0]); // Username
            row.createCell(1).setCellValue(teacherCredential[1]); // Password
        }

        // Resize columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }


    public static List<Map<String, String>> readCredentialsFromExcel(String filePath, String sheetName) {
        List<Map<String, String>> credentialsList = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Sheet not found: " + sheetName);
                return credentialsList; // Return empty list if sheet does not exist
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;  // Skip header row

                Map<String, String> credentials = new HashMap<>();
                credentials.put("username", row.getCell(0).getStringCellValue());
                credentials.put("password", row.getCell(1).getStringCellValue());

                credentialsList.add(credentials);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return credentialsList;
    }

}
