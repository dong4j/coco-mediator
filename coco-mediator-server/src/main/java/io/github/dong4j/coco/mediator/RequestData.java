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
 * @since x.x.x
 */
@Getter
@Builder
public class RequestData {
    private String apiName;
    private byte[] data;
    private String version;
    private String appid;
    private Long timestamp;
    private String sign;
    private String nonce;
    private String signatureHeader;
    private String charset;
    private String method;

}
