package io.github.dong4j.coco.mediator;

import cn.hutool.core.lang.UUID;

/**
 * <p>Description:  </p>
 *
 * @author dong4j
 * @version 1.0.0
 * @email "mailto:dong4j@gmail.com"
 * @date 2022.11.30 17:49
 * @since 2023.1.1
 */
public class MediatorHandlerImpl implements MediatorHandler {
    /**
     * Handle
     *
     * @param requestData request data
     * @return the byte [ ]
     * @since 2023.1.1
     */
    @Override
    public byte[] handle(RequestData requestData) {

        String requestId = UUID.fastUUID().toString(true);

        return requestData.getData();
    }
}
