package app;

import app.pages.classes.MyClassesPage;
import app.pages.login.LogInGooglePage;
import app.pages.login.LogInUsernamePage;
import app.pages.quizPage.DashboardPage;
import app.pages.signup.*;

public class PageBuilder {

    public static LogInUsernamePage buildLoginUsernamePage() {
        return new LogInUsernamePage("/auth/login");
    }
    public static LogInGooglePage buildLoginGooglePage() {
        return new LogInGooglePage("/auth/login");
    }
    public static DashboardPage buildDashboardPage() {
        return new DashboardPage("/app/student/dashboard");
    }
    public static MyClassesPage buildMyClassesPage() {
        return new MyClassesPage("/app/teacher/class/list");
    }
    public static StudentSignUpPage bulidStudentSignUpPage() {return new StudentSignUpPage("/app/sign-up/create-account/student");}
    public static SignUpSelectRolePage buildSignUpSelectRolePage() {return new SignUpSelectRolePage("/app/sign-up/role");}
    public static TeacherSignupStepOnePage buildTeacherSignupStepOnePage() {return new TeacherSignupStepOnePage("/app/sign-up/create-account/teacher");}
    public static TeacherSignupStepTwoPage buildTeacherSignupStepTwoPage() {return new TeacherSignupStepTwoPage("/app/sign-up/more-info");}
    public static TeacherSignUpStepThreePage buildTeacherSignUpStepThreePage() {return new TeacherSignUpStepThreePage("/app/sign-up/school-info");}


}
