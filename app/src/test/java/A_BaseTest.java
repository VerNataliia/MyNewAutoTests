import app.App;
import app.helpers.Driver;
import app.pages.headerMenu.TeacherHeaderMenu;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class A_BaseTest {

    @BeforeSuite(alwaysRun = true)
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
        );
    }
    protected App app;
    protected TeacherHeaderMenu teacherHeaderMenu;
    protected SoftAssert softAssert;
    //protected Logger logger;

    @BeforeClass (alwaysRun = true)
    public void setUp() {

        Driver.initDriver();

        app = new App();
        teacherHeaderMenu = new TeacherHeaderMenu();
        softAssert = new SoftAssert();

        //logger = (Logger) LogManager.getLogger("");
        //DOMConfigurator.configure("src/main/resources/log4j.xml");
    }

    @AfterMethod(alwaysRun = true)
    public void clearCookies() {
        Driver.clearCookies();
    }

    @AfterClass (alwaysRun = true)
    public void tearDown() {
        Driver.close();
    }
}
