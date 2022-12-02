/*
 * MIT License
 *
 * Copyright (c) $year dong4j
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
