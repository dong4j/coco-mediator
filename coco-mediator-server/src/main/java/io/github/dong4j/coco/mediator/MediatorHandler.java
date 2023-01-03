package io.github.dong4j.coco.mediator;

/**
 * <p>Description: </p>
 *
 * @author dong4j
 * @version 1.0.0
 * @email "mailto:dong4j@gmail.com"
 * @date 2022.11.30 14:51
 * @since 2023.1.1
 */
@SuppressWarnings("checkstyle:ParameterNumber")
public interface MediatorHandler {

    /**
     * Handle response entity
     *
     * @param requestData request data
     * @return the response entity
     * @since 1.0.0
     */
    byte[] handle(RequestData requestData);
}
