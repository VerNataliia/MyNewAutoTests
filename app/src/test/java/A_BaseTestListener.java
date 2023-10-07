//
//import app.helpers.Driver;
//import com.codeborne.selenide.Selenide;
//import io.qameta.allure.Allure;
//import org.openqa.selenium.OutputType;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//public class A_BaseTestListener implements ITestListener {
//
//    @Override
//    public void onTestFailure(ITestResult TestResult) {
//        Allure.getLifecycle().addAttachment("screenshot", "img/png", "png", Selenide.screenshot(OutputType.BYTES));
//        Driver.close();
//    }
//}
//
//
