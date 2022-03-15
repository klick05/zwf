package com.zwf.swagger.support;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import java.util.Optional;

/**
 * 描述: SwaggerUiHandler
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 21:43:12
 */
@Slf4j
@RequiredArgsConstructor
public class SwaggerUiHandler implements HandlerFunction<ServerResponse> {

    private final UiConfiguration uiConfiguration;

    /**
     * Handle the given request.
     * @param request the request to handler
     * @return the response
     */
    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(BodyInserters
                .fromValue(Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build())));
    }

}
