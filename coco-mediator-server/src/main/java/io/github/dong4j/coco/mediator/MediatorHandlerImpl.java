package io.github.dong4j.coco.mediator;

import cn.hutool.core.lang.UUID;

/**
 * <p>Description:  </p>
 *
 * @author dong4j
 * @version 1.0.0
 * @email "mailto:dong4j@gmail.com"
 * @date 2022.11.30 17:49
 * @since x.x.x
 */
public class MediatorHandlerImpl implements MediatorHandler {
    @Override
    public byte[] handle(RequestData requestData) {

        String requestId = UUID.fastUUID().toString(true);

        return requestData.getData();
    }
}
