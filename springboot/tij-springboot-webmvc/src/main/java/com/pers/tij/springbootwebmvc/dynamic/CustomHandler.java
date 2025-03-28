package com.pers.tij.springbootwebmvc.dynamic;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Optional;

/**
 * @author zachary
 */
@Component
public class CustomHandler {

    public ServerResponse handle(ServerRequest request) {
        Optional<String> param = request.param("param");
        return ServerResponse.ok().body("echo" + param.orElse(""));
    }
}
