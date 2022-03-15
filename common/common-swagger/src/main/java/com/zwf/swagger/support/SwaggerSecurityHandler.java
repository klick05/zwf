package com.zwf.swagger.support;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.util.Optional;

/**
 * 描述: SwaggerSecurityHandler
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 21:42:47
 */
@RequiredArgsConstructor
public class SwaggerSecurityHandler implements HandlerFunction<ServerResponse> {

    private final SecurityConfiguration securityConfiguration;

    /**
     * Handle the given request.
     * @param request the request to handler
     * @return the response
     */
    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(Optional.ofNullable(securityConfiguration)
                        .orElse(SecurityConfigurationBuilder.builder().build())));
    }

}
