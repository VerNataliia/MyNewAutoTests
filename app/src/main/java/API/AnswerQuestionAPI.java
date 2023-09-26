//package API;
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//
//import static app.AppConfig.UrlsAPI.ANSWER_QUESTION;
//import static app.AppConfig.teacherLogInData.TEACHER_PASSWORD;
//import static app.AppConfig.teacherLogInData.TEACHER_USERNAME;
//
//public class AnswerQuestionAPI {
//
//    public static void sendAnswer(int answerId, int questionId) {
//        String authorization = LogInAPI.getToken(TEACHER_USERNAME, TEACHER_PASSWORD);
//
//        RestAssured.baseURI = ANSWER_QUESTION;
//
//        Response response = RestAssured
//                .given()
//                .header("authorization", authorization)
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .header("cookie", "AWSALBAPP-1=_remove_; AWSALBAPP-2=_remove_; AWSALBAPP-3=_remove_; _gcl_au=1.1.559062066.1688658483; _fbp=fb.1.1688658488941.1920304659; _cc_id=547c8c1deea87f97eb1fcda714676703; _gid=GA1.2.902694025.1689573052; _hjSessionUser_2429165=eyJpZCI6IjE0YTZlNTg1LTIyOTUtNWIzYS04NDkwLWZhNDkzN2Q2MjM5NCIsImNyZWF0ZWQiOjE2ODkyMjUyNDEyODQsImV4aXN0aW5nIjp0cnVlfQ==; panoramaId_expiry=1690270410611; panoramaId=3acbe453263ec4292fb792ad162c16d5393872d21161a8e28a82721afcd491d5; panoramaIdType=panoIndiv; customerly_jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2N1c3RvbWVybHkuaW8iLCJqdGkiOiJjZjJmOGJjNi0yNzBiLTExZWUtYjRlNS0wMjQyMGEwMDAwMDQiLCJpYXQiOjE2ODk4NjQyMTYuOTE4NzgyLCJuYmYiOjE2ODk4NjQyMTYuOTE4Nzg2LCJleHAiOjI2NjgxNzE0MTYuOTE4Nzg4LCJ0eXBlIjo0LCJhcHAiOiI0YThiMmEzNiIsImlkIjoyOTQwMzc5N30.bQYqN0d5it_oNkhYZe_d6BRMsI2yC-3KKlUiiEFtZbA; rt_stick=074d9dca053fcdde6; _ga_1ZE6EWXTZY=GS1.1.1690043195.35.0.1690043195.0.0.0; _clck=q5urug|2|fdj|0|1286; rt_abt=1_1; JSESSIONID=367AA15762B3BD21E1FF0A3B844C7D7D; _ga=GA1.1.1650360636.1688658482; _ga_PDT6EPF20V=GS1.1.1690136117.51.1.1690136236.60.0.0; _clsk=1gd2kt0|1690136237462|6|1|t.clarity.ms/collect; AWSALBAPP-0=AAAAAAAAAACmIe0Q49HC8V5cbAmqHBc3ioJfsCsQI7a/Ip2PQ0i6VQyICEOKb1DzqQAPh/8aC9ILV+zysdkS1TXiur9Ril88Kh7CVgAQOZ/DqGtvTkV4SRrqXjjXpLDG5e4Qaec0CyrOpQ==; AWSALBAPP-0=AAAAAAAAAAAhmIf2BZW2H89ZKP9lpTkg0YUBBOWMSSpDEBwIW7nZd2lzdJ1tNLc0g3uAZobAIYR0t0MUITXL5kS+OKJAGXr4DwHnquWqWJGrioFzHqtJEZ0Hutghq126SEtj9mj2wUUi6w==; AWSALBAPP-1=_remove_; AWSALBAPP-2=_remove_; AWSALBAPP-3=_remove_; JSESSIONID=DD6693A5D7EBFC9DA6C52864C13260BA; rt_abt=1_1; rt_stick=074d9dca053fcdde6")
//                .header("x-requested-with", "XMLHttpRequest")
//                .formParam("isSelected", "true")
//                .formParam("answerId", String.valueOf(answerId))
//                .formParam("parentId", String.valueOf(questionId))
//                .post();
//
//        int statusCode = response.getStatusCode();
//        String responseBody = response.getBody().asString();
//
//        System.out.println("Status code: " + statusCode);
//        System.out.println("Response body: " + responseBody);
//    }
//}
