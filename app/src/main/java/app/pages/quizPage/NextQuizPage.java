//package app.pages.quizPage;
//
//import app.pages.base.BasePage;
//import com.codeborne.selenide.SelenideElement;
//import static com.codeborne.selenide.Selectors.*;
//import static com.codeborne.selenide.Selenide.*;
//import app.pages.base.BasePage;
//import java.util.List;
//import static app.AppConfig.UrlsUI.RESULT_QUIZ_PAGE;
//
//public class NextQuizPage extends BasePage {
//    public NextQuizPage(String pageUrl) {
//        super(pageUrl);
//    }
//
//    public SelenideElement quantityOfQuestions = $(byXpath("//div[@class = 'question-marker' or @class= 'question-marker markers-overlayed']"));
//
//    @FindBy(xpath = "//div[@class = 'question-marker' or @class= 'question-marker markers-overlayed']")
//    List<WebElement> quantityOfQuestions;
//
//    public SelenideElement currentQuizTitle = $(byXpath("//h2[@class='quiz-header-title']"));
//    public SelenideElement currentQuestionTitle = $(byXpath("//h3[@class='student-quiz-page__question'] | //h3[@class='student-quiz-page__question highlight'] | //h3[@class='student-quiz-page__question highlight']/p"));
//
//    WebElement answerOnCurrentQuestion;
//    public WebElement getAnswerOnCurrentQuestion(String correctAnswer) {
//        String correctAnswerXPath = "//p[@class='answer-card__body'][contains(text(), \"" + correctAnswer + "\")]";
//        String firstAnswerXPath = "//p[@class='answer-card__body'][1]";
//
//        try {
//            answerOnCurrentQuestion = driver.findElement(By.xpath(correctAnswerXPath));
//        } catch (NoSuchElementException e) {
//            System.out.println("Element not found with first XPath. Trying fallback XPath...");
//            answerOnCurrentQuestion = driver.findElement(By.xpath(firstAnswerXPath));
//        }
//firstAnswerXPath
//        return answerOnCurrentQuestion;
//    }
//
//    public SelenideElement firstAnswerOnQuestion = $(byXpath("//p[@class='answer-card__body'][1]"));
//
//    public SelenideElement submitAnswerButton = $(byXpath("//div[@class='primary-button student-quiz-page__question-submit quiz-tab-item focused']"));
//
//    public SelenideElement nextQuestionButton = $(byXpath("//div[@class='primary-button student-quiz-page__question-next next-btn quiz-tab-item focused']"));
//
//    public SelenideElement resultPopUp = $(byXpath("//body/div[@id='app']/div[@class='app-body']/div[@class='quiz-page-container']/div[@class='v--modal-overlay']/div[@class='v--modal-background-click']/div[@class='v--modal-box v--modal']/div[@class='VueCarousel']/div[@class='VueCarousel-wrapper']/div[@class='VueCarousel-inner']/div[1]/div[1]"));
//
//    public SelenideElement continueOnResultPopUpButton = $(byXpath("//div[@class='primary-button student-quiz-page__question-continue continue-btn quiz-tab-item focused']"));
//
//    public int getQuantityOfQuestions() {
//        int quantityOfUnansweredQuestions = quantityOfQuestions.size() + 1;
//        System.out.println("Quantity of non answered questions is " + quantityOfUnansweredQuestions);
//        return quantityOfUnansweredQuestions;
//    }
//
//    public String getCurrentQuizTitle() {
//        String currentQuizTitle = this.currentQuizTitle.getText();
//        System.out.println("Name of the current quiz is " + currentQuizTitle);
//        return currentQuizTitle;
//    }
//
//    public String getCurrentQuestionTitle() {
//        String currentQuestionTitle = this.currentQuestionTitle.getText();
//
//        // Split the text into lines and select the first line
//        String[] lines = currentQuestionTitle.split("\n");
//        String firstLine = lines[0];
//
//        System.out.println("Name of the current question is " + firstLine);
//        return firstLine;
//    }
//
//    public void selectCorrectAnswer() {
//        scrollToElement(answerOnCurrentQuestion);
//        answerOnCurrentQuestion.click();
//    }
//
//    public void selectFirstAnswer() {
//        firstAnswerOnQuestion.click();
//    }
//
//
//    public void clickOnSubmitAnswerButton() {
//        waitElementIsVisible(submitAnswerButton).isDisplayed();
//        try {
//            Thread.sleep(3000); // Wait for 3 seconds (3000 milliseconds)
//        } catch (InterruptedException e) {
//            // Handle the InterruptedException if necessary
//            e.printStackTrace();
//        }
//        submitAnswerButton.click();
//    }
//
//    public void clickOnNextQuestionButton() {
//        try {
//           waitElementIsClickable(nextQuestionButton);
//            nextQuestionButton.click();
//        } catch (StaleElementReferenceException | TimeoutException | ElementClickInterceptedException e) {
//            try {
//                // If the first click fails, try clicking again
//                waitElementIsClickable(nextQuestionButton);
//                nextQuestionButton.click();
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//    }
//
//
//
//    private boolean isResultQuizPageVisible() {
//        try {
//            return driver.getCurrentUrl().equals(RESULT_QUIZ_PAGE); // Check if the current URL is the RESULT_QUIZ_PAGE URL.
//        } catch (NoSuchElementException | StaleElementReferenceException e) {
//            return false;
//        }
//    }
//
//    public void clickOnContinueButtonOnResultPopUp() {
//        if (isResultPopUpDisplayed()) {
//            while (!isResultQuizPageVisible() && continueOnResultPopUpButton.isDisplayed()) {
//                continueOnResultPopUpButton.click();
//            }
//        }
//    }
//
//    public boolean isResultPopUpDisplayed() {
//        try {
//            return resultPopUp.isDisplayed();
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }
//}
