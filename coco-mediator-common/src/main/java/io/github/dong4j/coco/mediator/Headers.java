
/**
 * <p>Description:  </p>
 *
 * @author dong4j
 * @version 1.0.0
 * @email "mailto:dong4j@gmail.com"
 * @date 2022.11.30 15:00
 * @since 2023.1.1
 */
public class Headers {
    public static final String X_API_NAME = "X-Api-Name";

    public static final String X_API_VERSION = "X-Api-Version";
    public static final String X_API_STAGE = "X-Api-Stage";
    public static final String X_API_APPID = "X-Api-AppId";
    public static final String X_API_TENANTID = "X-Api-TenantId";
    /** X_API_SDK */
    public static final String X_API_SDK = "X-Api-SDK";
    /** X_API_APPNAME */
    public static final String X_API_APPNAME = "X-Api-AppName";
    /** X_API_TIMESTAMP */
    public static final String X_API_TIMESTAMP = "X-Api-Timestamp";
    /** X_API_TIMEOUT */
    public static final String X_API_TIMEOUT = "X-Api-Timeout";
    /** X_API_TOKEN */
    public static final String X_API_TOKEN = "X-Api-Token";
    /** 请求放重放Nonce,15分钟内保持唯一,建议使用UUID */
    public static final String X_API_NONCE = "X-Api-Nonce";
    /** X_API_GROUP */
    public static final String X_API_GROUP = "X-Api-Group";
    /** X_API_HOST */
    public static final String X_API_HOST = "X-Api-Host";
    /** X_API_MOCK */
    public static final String X_API_MOCK = "X-Api-Mock";
    /** 使用 endpoint 标识 */
    public static final String X_API_ENDPOINT = "X-Api-Endpoint";
    /** X_API_SIGNATURE */
    public static final String X_API_SIGNATURE = "X-Api-Signature";
    /** X_API_SIGNATURE_TYPE */
    public static final String X_API_SIGNATURE_TYPE = "X-Api-Signature-Type";
    /** 当前请求是否需要签名, 客户端写入, 当拦截器处理完成后删除 */
    public static final String X_API_NEED_SIGNATURE = "X-Api-Need-Signature";
    /** X_API_SIGNATURE_HEADERS */
    public static final String X_API_SIGNATURE_HEADERS = "X-Api-Signature-Headers";
    /** X_API_CHARSET */
    public static final String X_API_CHARSET = "X-Api-Charset";
    /** X_API_REQUEST_ID */
    public static final String X_API_REQUEST_ID = "X-Api-Request-Id";
}
