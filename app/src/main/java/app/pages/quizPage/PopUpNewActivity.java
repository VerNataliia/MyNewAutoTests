//package app.pages.quizPage;
//
//import app.pages.base.BasePage;
//import app.pages.base.BasePage;
//import com.codeborne.selenide.SelenideElement;
//import static com.codeborne.selenide.Selectors.*;
//import static com.codeborne.selenide.Selenide.*;
//
//public class PopUpNewActivity extends BasePage {
//    public PopUpNewActivity(String pageUrl) {
//        super(pageUrl);
//    }
//
//    public SelenideElement popUpYouWereAssignedNewActivity = $(byXpath("//div[@class='v--modal-box v--modal']"));
//    public SelenideElement doItLatterButton = $(byXpath("//div[@class='primary-button btn-later']"));
//    public SelenideElement startNowButton = $(byXpath("//div[@class='primary-button']"));
//
//    public boolean isPopUpDisplayed() {
//        try {
//            return waitElementIsVisible(popUpYouWereAssignedNewActivity).isDisplayed();
//        } catch (NoSuchElementException | TimeoutException e) {
//            return false; // Element not found or not displayed
//        }
//    }
//
//    public void skipAssignmentIfDisplayed() {
//        if (isPopUpDisplayed()) {
//            doItLatterButton.click();
//        }
//    }
//
//}
