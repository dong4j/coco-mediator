package io.github.dong4j.coco.mediator;

import lombok.Builder;
import lombok.Getter;

/**
 * <p>Description:  </p>
 *
 * @author dong4j
 * @version 1.0.0
 * @email "mailto:dong4j@gmail.com"
 * @date 2022.11.30 17:24
 * @since 2023.1.1
 */
@Getter
@Builder
public class RequestData {
    /** Api name */
    private String apiName;
    /** Data */
    private byte[] data;
    /** Version */
    private String version;
    /** Appid */
    private String appid;
    /** Timestamp */
    private Long timestamp;
    /** Sign */
    private String sign;
    /** Nonce */
    private String nonce;
    /** Signature header */
    private String signatureHeader;
    /** Charset */
    private String charset;
    /** Method */
    private String method;

}
