package com.pers.tij.springbootwebmvc;

import com.pers.tij.springbootwebmvc.dynamic.CustomHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

/**
 * @author zachary
 */
@Slf4j
@Configuration
public class ExampleRouteFunction {

    @Bean
    public RouterFunction<ServerResponse> normal(CustomHandler customHandler) {
        return route(GET("route_function/normal"), customHandler::handle) //
                .filter((req, next) -> {
                    log.info("filter...");
                    return next.handle(req);
                });
    }

    @Bean
    public RouterFunction<ServerResponse> multi() {
        return route() //
                .GET("route_function/multi/1", x -> {
                    log.info("multi...");
                    return ServerResponse.ok().body("1");
                }) //
                .GET("route_function/multi/2", x -> ServerResponse.ok().body("2")) //
                .filter((req, next) -> {
                    log.info("filter multi...");
                    return next.handle(req);
                })//
                .build();
    }
}
