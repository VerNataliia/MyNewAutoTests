import app.App;

public class UtilityCreateStudentsAsTeacher extends A_BaseTest {
    public static void createNewStudentsAsTeacher(App app) {
        app.classPage.clickOnAddNewStudentsButton();
        app.classPage.createNewStudent();
        app.classPage.assertThatStudentAppearsOnClassList("("+app.classPage.getNewStudentUsername()+")");
        
    }
}
