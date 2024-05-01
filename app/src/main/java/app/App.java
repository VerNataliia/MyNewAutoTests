package app;

import app.pages.BO.BO;
import app.pages.activities.ActivityCreation;
import app.pages.activities.ActivityHomePage;
import app.pages.classes.ClassPage;
import app.pages.classes.CreateEditClassDrawer;
import app.pages.classes.MyClassesPage;
import app.pages.headerMenu.StudentHeaderMenu;
import app.pages.headerMenu.TeacherHeaderMenu;
import app.pages.login.LogInPage;
import app.pages.myProgress.MyProgressPage;
import app.pages.quizPage.NextQuizPage;
import app.pages.quizPage.ResultPage;
import app.pages.quizPage.newPretest.NewPretestPage;
import app.pages.pricing.PricingPage;
import app.pages.quizPage.DashboardPage;
import app.pages.signup.*;
import app.pages.signup.studentAndParent.ParentSignUpPage;
import app.pages.signup.studentAndParent.StudentOrParentPersonalDetailsStepPage;
import app.pages.signup.studentAndParent.StudentSignUpPage;
import app.pages.signup.teacher.TeacherSignUpStepFourPage;
import app.pages.signup.teacher.TeacherSignUpStepThreePage;
import app.pages.signup.teacher.TeacherSignupStepOnePage;
import app.pages.signup.teacher.TeacherSignupStepTwoPage;
import app.pages.students.AddNewStudentsPage;
import app.pages.summaryPage.SummaryPage;
import app.pages.userSettings.SocialLinking;
import app.pages.userSettings.StudentMyTeachers;
import app.pages.userSettings.StudentProfileSettings;
import app.pages.userSettings.TeacherProfileSettings;


public class App {

    public LogInPage logInPage;
    public DashboardPage dashboardPage;
    public MyClassesPage myClassesPage;
    public ClassPage classPage;

    public SignUpSelectRolePage signUpSelectRolePage;
    public StudentSignUpPage studentSignUpPage;
    public StudentOrParentPersonalDetailsStepPage studentOrParentPersonalDetailsStepPage;
    public ParentSignUpPage parentSignUpPage;
    public TeacherSignupStepOnePage teacherSignupStepOnePage;
    public TeacherSignupStepTwoPage teacherSignupStepTwoPage;
    public TeacherSignUpStepThreePage teacherSignUpStepThreePage;
    public TeacherSignUpStepFourPage teacherSignUpStepFourPage;
    public GoogleSignUpPage googleSignUpPage;
    public MicrosoftSignUpPage microsoftSignUpPage;
    public CleverSignUpPage cleverSignUpPage;
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
    public SocialLinking socialLinking;
    public StudentMyTeachers studentMyTeachers;
    public AddNewStudentsPage addNewStudentsPage;
    public ActivityHomePage activityHomePage;
    public ActivityCreation activityCreation;
    public MyProgressPage myProgressPage;
    public BO backOffice;


    public App() {
        logInPage = new LogInPage("/auth/login");
        dashboardPage = new DashboardPage("/app/student/dashboard");
        myClassesPage = new MyClassesPage("/app/teacher/class/list");
        classPage = new ClassPage("app/teacher/class/1340742"); // Need to think how to send dynamic class_id
        signUpSelectRolePage = new SignUpSelectRolePage("/app/sign-up/role");
        studentSignUpPage = new StudentSignUpPage("/app/sign-up/create-account/student");
        studentOrParentPersonalDetailsStepPage = new StudentOrParentPersonalDetailsStepPage("/app/sign-up/more-info");
        parentSignUpPage = new ParentSignUpPage("/app/sign-up/create-account/parent");
        teacherSignupStepOnePage = new TeacherSignupStepOnePage("/app/sign-up/create-account/teacher");
        teacherSignupStepTwoPage = new TeacherSignupStepTwoPage("/app/sign-up/more-info");
        teacherSignUpStepThreePage = new TeacherSignUpStepThreePage("/app/sign-up/school-info");
        teacherSignUpStepFourPage = new TeacherSignUpStepFourPage("/app/sign-up/pricing");
        googleSignUpPage = new GoogleSignUpPage();
        microsoftSignUpPage = new MicrosoftSignUpPage();
        cleverSignUpPage = new CleverSignUpPage();
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
        socialLinking = new SocialLinking();
        studentMyTeachers = new StudentMyTeachers();
        addNewStudentsPage = new AddNewStudentsPage();
        activityCreation = new ActivityCreation();
        activityHomePage = new ActivityHomePage("/app/teacher/activities");
        backOffice = new BO("https://bo2.readtheory.org/users");
        myProgressPage = new MyProgressPage("/app/student/report");

    }
}

