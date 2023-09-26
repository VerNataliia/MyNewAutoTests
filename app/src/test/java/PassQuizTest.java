//package common.tests;
//
//import org.testng.annotations.Test;
//
//import static app.AppConfig.UrlsUI.LOG_IN_PAGE;
//import static app.AppConfig.teacherLogInData.TEACHER_PASSWORD;
//import static app.AppConfig.teacherLogInData.TEACHER_USERNAME;
//
//
//public class PassQuizTest extends PagesInitialization {
//    @Test
//    public void checkStudentLogIn() {
//        //Log in and start quiz
//        basePage.open(LOG_IN_PAGE);
//        login.logInWithUsername(TEACHER_USERNAME, TEACHER_PASSWORD);
//        dashboard.clickOnStartPracticingButton();
//        // Repeat 5 times
//        for (int repeat = 0; repeat < 10; repeat++) {
//            popUpNewActivity.skipAssignmentIfDisplayed();
//            int numberOfQuestions = nextQuiz.getQuantityOfQuestions();
//
//            boolean isCorrectCycle = (repeat == 1) || (repeat == 3) || (repeat == 6);
//
//            while (!nextQuiz.isResultPopUpDisplayed()) {
//                for (int i = 0; i < numberOfQuestions; i++) {
//                    String currentQuiz = nextQuiz.getCurrentQuizTitle();
//                    String currentQuestion = nextQuiz.getCurrentQuestionTitle();
//                    String correctAnswer = extractor.extractAnswer(currentQuiz, currentQuestion);
//
//                    if (isCorrectCycle) {
//                        nextQuiz.getAnswerOnCurrentQuestion(correctAnswer);
//                        nextQuiz.selectCorrectAnswer();
//                    } else {
//                        // Randomly choose between selectCorrectAnswer and selectFirstAnswer
//                        if (random.nextBoolean()) {
//                            nextQuiz.getAnswerOnCurrentQuestion(correctAnswer);
//                            nextQuiz.selectCorrectAnswer();
//                        } else {
//                            nextQuiz.selectFirstAnswer();
//                        }
//                    }
//
//                    nextQuiz.clickOnSubmitAnswerButton();
//                    nextQuiz.clickOnNextQuestionButton();
//                }
//            }
//
//            // Finish quiz and go to the result page
//            nextQuiz.clickOnContinueButtonOnResultPopUp();
//
//            // Move to next quiz
//            resultPage.clickOnNextQuizButton();
//        }
//
//    }
//}
