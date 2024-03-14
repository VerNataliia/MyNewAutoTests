package app;

import app.pages.classes.ClassPage;
import app.pages.classes.CreateEditClassDrawer;
import app.pages.classes.MyClassesPage;
import app.pages.headerMenu.StudentHeaderMenu;
import app.pages.headerMenu.TeacherHeaderMenu;
import app.pages.login.LogInGooglePage;
import app.pages.login.LogInUsernamePage;
import app.pages.quizPage.NextQuizPage;
import app.pages.quizPage.ResultPage;
import app.pages.quizPage.newPretest.NewPretestPage;
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
import app.pages.students.AddNewStudentsPage;
import app.pages.summaryPage.SummaryPage;
import app.pages.userSettings.StudentProfileSettings;
import app.pages.userSettings.TeacherProfileSettings;


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
    public NewPretestPage newPretestPage;
    public NextQuizPage nextQuizPage;
    public ResultPage resultPage;
    public PricingPage pricingPage;
    public TeacherHeaderMenu teacherHeaderMenu;
    public StudentHeaderMenu studentHeaderMenu;
    public CreateEditClassDrawer createEditClassDrawer;
    public TeacherProfileSettings teacherProfileSettings;
    public StudentProfileSettings studentProfileSettings;
    public AddNewStudentsPage addNewStudentsPage;


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
        newPretestPage = new NewPretestPage("https://staging.readtheory.org/app/v2/student/pretest");
        nextQuizPage = new NextQuizPage("/app/student/quiz");
        resultPage = new ResultPage("/app/student/quiz/results");
        pricingPage = new PricingPage("/app/sign-up/pricing");
        teacherHeaderMenu = new TeacherHeaderMenu();
        studentHeaderMenu = new StudentHeaderMenu();
        createEditClassDrawer = new CreateEditClassDrawer();
        teacherProfileSettings = new TeacherProfileSettings();
        studentProfileSettings = new StudentProfileSettings();
        addNewStudentsPage = new AddNewStudentsPage();

    }
}

