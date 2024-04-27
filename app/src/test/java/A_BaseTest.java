import app.App;
import app.helpers.Driver;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.logging.log4j.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class A_BaseTest {

    @BeforeSuite(alwaysRun = true)
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
        );
    }
    protected static App app;
    protected SoftAssert softAssert;
    protected Logger logger;

    @BeforeClass (alwaysRun = true)
    public void setUp() {

        Driver.initDriver();

        app = new App();
        softAssert = new SoftAssert();
        logger = LogManager.getLogger(A_BaseTest.class);
    }

    @AfterMethod(alwaysRun = true)
    public void clearCookies() {
        Driver.clearCookies();
        Driver.close();
    }

//    @AfterClass (alwaysRun = true)
//    public void tearDown() {
//        Driver.close();
//    }
}
