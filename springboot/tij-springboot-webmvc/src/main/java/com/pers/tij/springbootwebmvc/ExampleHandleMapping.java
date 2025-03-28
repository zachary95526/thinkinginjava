package com.pers.tij.springbootwebmvc;

import com.pers.tij.springbootwebmvc.dynamic.DynamicController;
import jakarta.annotation.PostConstruct;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author zachary
 */
@RestController
@RequestMapping("example")
public class ExampleHandleMapping {
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    public ExampleHandleMapping(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    @PostConstruct
    public void init() throws Exception {
        // 手动注册路由
        RequestMappingInfo mapping = RequestMappingInfo.paths("/dynamic").methods(RequestMethod.GET).build();
        DynamicController controller = new DynamicController();
        Method method = ReflectionUtils.findMethod(DynamicController.class, "execute", String.class);
        assert method != null;
        requestMappingHandlerMapping.registerMapping(mapping, controller, method);
    }

    @RequestMapping(value = "normal", params = "action=xxx")
    public String normal(@RequestParam(value = "param", required = false) String param) {
        return param;
    }

    @GetMapping(value = "path/{input}")
    public String path(@PathVariable("input") String input) {
        return input;
    }
}
