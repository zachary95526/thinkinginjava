package com.pers.tij.springbootwebmvc.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zachary
 */
@Slf4j
public class DynamicController {

    @ResponseBody
    public String execute(@RequestParam("param") String param) {
        return param;
    }
}
