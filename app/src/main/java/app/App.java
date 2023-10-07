package app;

import app.pages.classes.MyClassesPage;
import app.pages.login.LogInGooglePage;
import app.pages.login.LogInUsernamePage;
import app.pages.quizPage.DashboardPage;
import app.pages.signup.SignUpSelectRolePage;
import app.pages.signup.StudentSignUpPage;

public class App {

    public LogInUsernamePage logInUsernamePage;
    public LogInGooglePage logInGooglePage;
    public DashboardPage dashboardPage;
    public MyClassesPage myClassesPage;
    public StudentSignUpPage studentSignUpPage;
    public SignUpSelectRolePage signUpSelectRolePage;

    public App() {
        logInUsernamePage = PageBuilder.buildLoginUsernamePage();
        logInGooglePage = PageBuilder.buildLoginGooglePage();
        dashboardPage = PageBuilder.buildDashboardPage();
        myClassesPage = PageBuilder.buildMyClassesPage();
        studentSignUpPage = PageBuilder.bulidStudentSignUpPage();
        signUpSelectRolePage = PageBuilder.buildSignUpSelectRolePage();
    }
}
