package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(TestLogFilter.class);

    private final Prettifier prettifier;

    public TestLogFilter() {
        this.prettifier = new Prettifier();
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);

        logger.info(
                "\n" + requestSpec.getMethod() + " " + requestSpec.getURI() +
                        "\n" + requestSpec.getHeaders() +
                        "\n" + (requestSpec.getBody() == null ? "<empty body>" : prettifier.getPrettifiedBodyIfPossible(requestSpec)) +
                        "\n" + response.getStatusLine() +
                        "\n" + (response.getBody().asString().isEmpty() ? "<empty body>" : prettifier.getPrettifiedBodyIfPossible(response, response.getBody())) +
                        "\n");
        return response;
    }
}
