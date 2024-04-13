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
import java.util.*;

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
            String studentUsername = useNames ? app.addNewStudentsPage.addRandomStudentUsernameFromName() : app.addNewStudentsPage.addRandomStudentUsername();
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

    public static void saveCredentialsToExcel(String fileName, Object credentials, String[] teacherCredential, boolean classInclude) {
        Workbook workbook = new XSSFWorkbook();
        String[] studentColumns = classInclude ? new String[]{"Class", "Username", "Password"} : new String[]{"Username", "Password"};

        // Create and fill Student Credentials sheet
        Sheet studentSheet = workbook.createSheet("Student Credentials");
        fillSheet(studentSheet, studentColumns, credentials, teacherCredential, classInclude);

        // Create and fill Teacher Credentials sheet
        Sheet teacherSheet = workbook.createSheet("Teacher Credentials");
        fillSheet(teacherSheet, new String[]{"Username", "Password"}, null, teacherCredential, false);

        // Write the workbook to a file
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filePath = "src/main/resources/files/" + fileName + formattedDate + ".xlsx";

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Saved Excel file to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillSheet(Sheet sheet, String[] columns, Object credentials, String[] teacherCredential, boolean classInclude) {
        // Creating header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        if (classInclude && credentials instanceof List) {
            // Filling class student data
            List<Map<Integer, List<Map<String, String>>>> classStudentDetails = (List<Map<Integer, List<Map<String, String>>>>) credentials;
            int rowNum = 1;
            for (Map<Integer, List<Map<String, String>>> classDetail : classStudentDetails) {
                for (Map.Entry<Integer, List<Map<String, String>>> entry : classDetail.entrySet()) {
                    int classNumber = entry.getKey();
                    for (Map<String, String> studentCredentials : entry.getValue()) {
                        Row row = sheet.createRow(rowNum++);
                        row.createCell(0).setCellValue(classNumber);
                        row.createCell(1).setCellValue(studentCredentials.get("username"));
                        row.createCell(2).setCellValue(studentCredentials.get("password"));
                    }
                }
            }
        } else if (credentials instanceof List) {
            // Filling student data
            List<Map<String, String>> studentCredential = (List<Map<String, String>>) credentials;
            int rowNum = 1;
            for (Map<String, String> studentCredentials : studentCredential) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(studentCredentials.get("username"));
                row.createCell(1).setCellValue(studentCredentials.get("password"));
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
    public static Object readCredentialsFromExcel(String filePath, String sheetName, boolean classInclude) {
        List<Map<String, String>> credentialsList = new ArrayList<>();
        List<Map<Integer, List<Map<String, String>>>> classStudentDetails = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Sheet not found: " + sheetName);
                return credentialsList; // Return empty list if sheet does not exist
            }

            Iterator<Row> rowIterator = sheet.iterator();
            if (!rowIterator.hasNext()) return null; // No rows in sheet

            Row headerRow = rowIterator.next();
            int columnCount = headerRow.getPhysicalNumberOfCells();

            if (classInclude && columnCount > 2) {
                Map<Integer, List<Map<String, String>>> classMap = new HashMap<>();
                int currentClass = -1;

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    int classNumber = (int) row.getCell(0).getNumericCellValue();
                    String username = row.getCell(1).getStringCellValue();
                    String password = row.getCell(2).getStringCellValue();

                    if (classNumber != currentClass) {
                        if (currentClass != -1) {
                            classStudentDetails.add(classMap);
                        }
                        classMap = new HashMap<>();
                        currentClass = classNumber;
                    }

                    classMap.computeIfAbsent(classNumber, k -> new ArrayList<>())
                        .add(Map.of("username", username, "password", password));
                }

                if (!classMap.isEmpty()) {
                    classStudentDetails.add(classMap);
                }

                return classStudentDetails;
            } else {
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    String username = row.getCell(0).getStringCellValue();
                    String password = row.getCell(1).getStringCellValue();

                    Map<String, String> credentials = new HashMap<>();
                    credentials.put("username", username);
                    credentials.put("password", password);
                    credentialsList.add(credentials);
                }

                return credentialsList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

//    public static void saveCredentialsToExcel(List<Map<String, String>> studentCredential, String[] teacherCredential) {
//        String[] columns = {"Username", "Password"};
//        Workbook workbook = new XSSFWorkbook();
//
//        // Create and fill Student Credentials sheet
//        Sheet studentSheet = workbook.createSheet("Student Credentials");
//        fillSheet(studentSheet, columns, studentCredential, null);
//
//        // Create and fill Teacher Credentials sheet
//        Sheet teacherSheet = workbook.createSheet("Teacher Credentials");
//        fillSheet(teacherSheet, columns, null, teacherCredential);
//
//        // Write the workbook to a file
//        LocalDate currentDate = LocalDate.now();
//        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        String filePath = "src/main/resources/files/Credentials_" + formattedDate + ".xlsx";
//
//        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
//            workbook.write(fileOut);
//            System.out.println("Saved Excel file to: " + filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void fillSheet(Sheet sheet, String[] columns, List<Map<String, String>> studentCredential, String[] teacherCredential) {
//        // Creating header row
//        Row headerRow = sheet.createRow(0);
//        for (int i = 0; i < columns.length; i++) {
//            Cell cell = headerRow.createCell(i);
//            cell.setCellValue(columns[i]);
//        }
//
//        if (studentCredential != null) {
//            // Filling student data
//            int rowNum = 1;
//            for (Map<String, String> credentials : studentCredential) {
//                Row row = sheet.createRow(rowNum++);
//
//                row.createCell(0).setCellValue(credentials.get("username"));
//                row.createCell(1).setCellValue(credentials.get("password"));
//            }
//        } else if (teacherCredential != null) {
//            // Filling teacher data
//            Row row = sheet.createRow(1); // Assuming only one row for teacher credentials
//            row.createCell(0).setCellValue(teacherCredential[0]); // Username
//            row.createCell(1).setCellValue(teacherCredential[1]); // Password
//        }
//
//        // Resize columns to fit the content size
//        for (int i = 0; i < columns.length; i++) {
//            sheet.autoSizeColumn(i);
//        }
//    }
//
//
//    public static List<Map<String, String>> readCredentialsFromExcel(String filePath, String sheetName) {
//        List<Map<String, String>> credentialsList = new ArrayList<>();
//
//        try (FileInputStream fileIn = new FileInputStream(filePath);
//             Workbook workbook = new XSSFWorkbook(fileIn)) {
//
//            Sheet sheet = workbook.getSheet(sheetName);
//            if (sheet == null) {
//                System.out.println("Sheet not found: " + sheetName);
//                return credentialsList; // Return empty list if sheet does not exist
//            }
//
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) continue;  // Skip header row
//
//                Map<String, String> credentials = new HashMap<>();
//                credentials.put("username", row.getCell(0).getStringCellValue());
//                credentials.put("password", row.getCell(1).getStringCellValue());
//
//                credentialsList.add(credentials);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return credentialsList;
//    }

