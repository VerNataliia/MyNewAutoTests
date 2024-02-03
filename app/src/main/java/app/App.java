package app;

import app.pages.classes.MyClassesPage;
import app.pages.login.LogInGooglePage;
import app.pages.login.LogInUsernamePage;
import app.pages.pretest.PretestPage;
import app.pages.quizPage.DashboardPage;
import app.pages.signup.*;
import app.pages.summaryPage.SummaryPage;


public class App {

    public LogInUsernamePage logInUsernamePage;
    public LogInGooglePage logInGooglePage;
    public DashboardPage dashboardPage;
    public MyClassesPage myClassesPage;
    public StudentSignUpPage studentSignUpPage;
    public SignUpSelectRolePage signUpSelectRolePage;
    public TeacherSignupStepOnePage teacherSignupStepOnePage;
    public TeacherSignupStepTwoPage teacherSignupStepTwoPage;
    public TeacherSignUpStepThreePage teacherSignUpStepThreePage;
    public SummaryPage summaryPage;
    public PretestPage pretestPage;


    public App() {
        logInUsernamePage = new LogInUsernamePage("/auth/login");
        logInGooglePage = new LogInGooglePage("/auth/login");
        dashboardPage = new DashboardPage("/app/student/dashboard");
        myClassesPage = new MyClassesPage("/app/teacher/class/list");
        studentSignUpPage = new StudentSignUpPage("/app/sign-up/create-account/student");
        signUpSelectRolePage = new SignUpSelectRolePage("/app/sign-up/role");
        teacherSignupStepOnePage = new TeacherSignupStepOnePage("/app/sign-up/create-account/teacher");
        teacherSignupStepTwoPage = new TeacherSignupStepTwoPage("/app/sign-up/more-info");
        teacherSignUpStepThreePage = new TeacherSignUpStepThreePage("/app/sign-up/school-info");
        summaryPage = new SummaryPage("/app/sign-up/summary");
        pretestPage = new PretestPage("https://staging.readtheory.org/app/v2/student/pretest");

    }
}

