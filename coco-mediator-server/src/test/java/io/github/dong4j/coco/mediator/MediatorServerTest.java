package io.github.dong4j.coco.mediator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;

/**
 * <p>Description:  </p>
 * <a href="https://vertx.io/docs/vertx-junit5/java/">...</a>
 *
 * @author dong4j
 * @version 1.0.0
 * @email "mailto:dong4j@gmail.com"
 * @date 2022.11.30 14:31
 * @since 2023.1.1
 */
@ExtendWith(VertxExtension.class)
class MediatorServerTest {

    /**
     * Deploy verticle
     *
     * @param vertx       vertx
     * @param testContext test context
     * @since 2023.1.1
     */
    @BeforeEach
    void deploy_verticle(Vertx vertx, VertxTestContext testContext) {
        vertx.deployVerticle(new MediatorServer(new MediatorHandlerImpl()), testContext.succeedingThenComplete());
    }

    /**
     * Http server check response
     *
     * @param vertx       vertx
     * @param testContext test context
     * @since 2023.1.1
     */
    @RepeatedTest(3)
    void http_server_check_response(Vertx vertx, VertxTestContext testContext) {
        HttpClient client = vertx.createHttpClient();
        client.request(HttpMethod.GET, 8080, "localhost", "/api/ping")
            .compose(req -> req.send().compose(HttpClientResponse::body))
            .onComplete(testContext.succeeding(buffer -> testContext.verify(() -> {
                Assertions.assertEquals(buffer.toString(), "pong");
                testContext.completeNow();
            })));
    }
}
