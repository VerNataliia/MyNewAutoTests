//package API;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//
//import static app.AppConfig.UrlsAPI.LOG_IN_API;
//
//public class LogInAPI {
//    public static String getToken(String username, String password) {
//        RestAssured.baseURI = LOG_IN_API;
//
//        String token;
//        token = RestAssured.given()
//                .header("Content-Type", ContentType.JSON.withCharset("UTF-8"))
//                .body("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}")
//                .post()
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .extract()
//                .path("token");
//
//        return "Bearer " + token;
//
//    }
//}
