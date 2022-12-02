package io.github.dong4j.coco.mediator;

import java.util.Base64;

import io.github.dong4j.coco.mediator.util.JsonUtils;
import io.netty.util.internal.StringUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Description:  </p>
 *
 * @author dong4j
 * @version 1.0.0
 * @email "mailto:dong4j@gmail.com"
 * @date 2022.11.30 14:04
 * @since 2023.1.1
 */
@Slf4j
@AllArgsConstructor
public class MediatorServer extends AbstractVerticle {
    /** Mediator handler */
    private MediatorHandler mediatorHandler;

    /**
     * Main
     *
     * @since 2023.1.1
     */
    @PostConstruct
    public void init() {
        Vertx vertx = Vertx.vertx();
        // 部署 verticle，这里会调用 start 方法
        vertx.deployVerticle(this);
    }

    /**
     * Start
     *
     * @since 2023.1.1
     */
    @Override
    public void start() {
        // 实例化一个路由器出来，用来路由不同的 rest 接口
        Router router = Router.router(vertx);
        // 增加一个处理器，将请求的上下文信息，放到 RoutingContext 中
        router.route().handler(BodyHandler.create());
        // 处理一个 post 方法的 rest 接口
        router.post("/api").handler(this::handlePost);
        // 处理一个 get 方法的 rest 接口
        router.get("/api").handler(this::handleGet);
        router.get("/api/time").handler(this::handleTime);
        router.get("/api/ping").handler(this::handlePing);
        // 创建一个 httpserver，监听 8080 端口，并交由路由器分发处理用户请求
        vertx.createHttpServer().requestHandler(router).listen(8080);
    }

    /**
     * Handle time
     *
     * @param context context
     * @since 2023.1.1
     */
    private void handleTime(RoutingContext context) {
        context.response().putHeader("content-type", "application/json").end(String.valueOf(System.currentTimeMillis()));
    }

    /**
     * Handle ping
     *
     * @param context context
     * @since 2023.1.1
     */
    private void handlePing(RoutingContext context) {
        context.response().putHeader("content-type", "application/json").end("pong");
    }

    /**
     * Handle post
     *
     * @param context context
     * @since 2023.1.1
     */
    private void handlePost(RoutingContext context) {
        final RequestData requestData = build(context.request(), context.body().buffer().getBytes());
        final byte[] result = mediatorHandler.handle(requestData);
        // 申明 response 类型为 json 格式，结束 response 并且输出 json 字符串
        context.response().putHeader("content-type", "application/json").end(new String(result));
    }

    /**
     * Handle get
     *
     * @param context context
     * @since 2023.1.1
     */
    private void handleGet(RoutingContext context) {
        final Base64.Encoder urlEncoder = Base64.getUrlEncoder();
        final byte[] data = urlEncoder.encode((JsonUtils.toJsonAsBytes(context.request().params().get("data"))));
        final RequestData requestData = build(context.request(), data);
        final byte[] result = mediatorHandler.handle(requestData);
        context.response().putHeader("content-type", "application/json").end(new String(result));
    }

    /**
     * Build
     *
     * @param request request
     * @param data    data
     * @return the request data
     * @since 2023.1.1
     */
    private RequestData build(HttpServerRequest request, byte[] data) {
        final HttpMethod method = request.method();
        final String methodName = method.name();
        final String apiName = request.getHeader(Headers.X_API_NAME);
        final String version = request.getHeader(Headers.X_API_VERSION);
        final String appid = request.getHeader(Headers.X_API_APPID);
        final String timestamp = request.getHeader(Headers.X_API_TIMESTAMP);
        final String sign = request.getHeader(Headers.X_API_SIGNATURE);
        final String nonce = request.getHeader(Headers.X_API_NONCE);
        final String signatureHeader = request.getHeader(Headers.X_API_SIGNATURE_HEADERS);
        final String charset = request.getHeader(Headers.X_API_CHARSET);

        return RequestData.builder().apiName(apiName)
            .version(version)
            .appid(appid)
            .timestamp(StringUtil.isNullOrEmpty(timestamp) ? null : Long.parseLong(timestamp))
            .sign(sign)
            .nonce(nonce)
            .signatureHeader(signatureHeader)
            .charset(charset)
            .method(methodName)
            .data(data)
            .build();
    }
}
