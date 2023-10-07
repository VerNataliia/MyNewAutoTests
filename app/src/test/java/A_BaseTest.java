import app.App;
import app.helpers.Driver;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

class A_BaseTest {

    @BeforeSuite
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
        );
    }
    protected App app;
    protected SoftAssert softAssert;
    //protected Logger logger;

    @BeforeMethod (alwaysRun = true)
    @BeforeClass
    public void setUp() {

        Driver.initDriver();

        app = new App();
        softAssert = new SoftAssert();

        //logger = (Logger) LogManager.getLogger("");
        //DOMConfigurator.configure("src/main/resources/log4j.xml");
    }

    @AfterMethod
    public void clearCookies() {
        Driver.clearCookies();
    }

    @AfterClass
    public void tearDown() {
        Driver.close();
    }
}
