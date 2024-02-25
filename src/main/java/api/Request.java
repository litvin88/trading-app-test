package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import utils.Formatter;
import utils.TestLogFilter;

@Slf4j
public class Request {

    public ValidatableResponse get(String endPoint, String... endPointParams) {
        return RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get(Formatter.formUrl(endPoint, endPointParams))
                .then();
    }

    public ValidatableResponse post(Object body, String endPoint, String... endPointParams) {
        RequestSpecification specification = RestAssured.given()
                .spec(requestSpecification())
                .when();
        specification.body(body);
        return specification.post(Formatter.formUrl(endPoint, endPointParams)).then();
    }

    private RequestSpecification requestSpecification() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setConfig(RestAssuredConfig.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false))
                        .httpClient(HttpClientConfig.httpClientConfig()
                                .setParam("http.connection.timeout", 10000)))
                .setUrlEncodingEnabled(false)
                .setContentType(ContentType.JSON);
        requestSpecBuilder.addFilter(new TestLogFilter());
        return requestSpecBuilder.build();
    }
}
