package app;

import app.pages.classes.ClassPage;
import app.pages.classes.MyClassesPage;
import app.pages.login.LogInGooglePage;
import app.pages.login.LogInUsernamePage;
import app.pages.pretest.PretestPage;
import app.pages.pricing.PricingPage;
import app.pages.quizPage.DashboardPage;
import app.pages.signup.*;
import app.pages.signup.parent.ParentSignUpPage;
import app.pages.signup.student.StudentSignUpAgeStepPage;
import app.pages.signup.student.StudentSignUpPage;
import app.pages.signup.teacher.TeacherSignUpStepFourPage;
import app.pages.signup.teacher.TeacherSignUpStepThreePage;
import app.pages.signup.teacher.TeacherSignupStepOnePage;
import app.pages.signup.teacher.TeacherSignupStepTwoPage;
import app.pages.summaryPage.SummaryPage;


public class App {

    public LogInUsernamePage logInUsernamePage;
    public LogInGooglePage logInGooglePage;
    public DashboardPage dashboardPage;
    public MyClassesPage myClassesPage;
    public ClassPage classPage;
    public SignUpSelectRolePage signUpSelectRolePage;
    public StudentSignUpPage studentSignUpPage;
    public StudentSignUpAgeStepPage studentSignUpAgeStepPage;
    public ParentSignUpPage parentSignUpPage;
    public TeacherSignupStepOnePage teacherSignupStepOnePage;
    public TeacherSignupStepTwoPage teacherSignupStepTwoPage;
    public TeacherSignUpStepThreePage teacherSignUpStepThreePage;
    public TeacherSignUpStepFourPage teacherSignUpStepFourPage;
    public SummaryPage summaryPage;
    public PretestPage pretestPage;
    public PricingPage pricingPage;


    public App() {
        logInUsernamePage = new LogInUsernamePage("/auth/login");
        logInGooglePage = new LogInGooglePage("/auth/login");
        dashboardPage = new DashboardPage("/app/student/dashboard");
        myClassesPage = new MyClassesPage("/app/teacher/class/list");
        classPage = new ClassPage("app/teacher/class/1340742"); // Need to think how to send dynamic class_id
        signUpSelectRolePage = new SignUpSelectRolePage("/app/sign-up/role");
        studentSignUpPage = new StudentSignUpPage("/app/sign-up/create-account/student");
        studentSignUpAgeStepPage = new StudentSignUpAgeStepPage("/app/sign-up/more-info");
        parentSignUpPage = new ParentSignUpPage("/app/sign-up/create-account/parent");
        teacherSignupStepOnePage = new TeacherSignupStepOnePage("/app/sign-up/create-account/teacher");
        teacherSignupStepTwoPage = new TeacherSignupStepTwoPage("/app/sign-up/more-info");
        teacherSignUpStepThreePage = new TeacherSignUpStepThreePage("/app/sign-up/school-info");
        teacherSignUpStepFourPage = new TeacherSignUpStepFourPage("/app/sign-up/pricing");
        summaryPage = new SummaryPage("/app/sign-up/summary");
        pretestPage = new PretestPage("https://staging.readtheory.org/app/v2/student/pretest");
        pricingPage = new PricingPage("/app/sign-up/pricing");

    }
}

