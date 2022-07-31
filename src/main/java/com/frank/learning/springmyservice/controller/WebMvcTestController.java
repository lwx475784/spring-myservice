package com.frank.learning.springmyservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.frank.learning.springmyservice.annotation.ReqAttr;
import com.frank.learning.springmyservice.model.dto.request.Foo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/web-mvc")
public class WebMvcTestController {

    private final ObjectMapper objectMapper;

    @PostMapping("/test")
    public Foo test(@ReqAttr("fooJson") Foo foo) {
        if (log.isDebugEnabled()) {
            log.debug("收到请求参数：{}", foo);
        }
        return foo;
    }

    @GetMapping("/test")
    public Foo testGet(/*@RequestAttribute("fooJson")*/ Foo foo) {
        if (log.isDebugEnabled()) {
            log.debug("收到请求参数：{}", foo);
        }
        return foo;
    }

    @ModelAttribute
    public void before(HttpServletRequest request) throws IOException {
//        String bodyJson = request.getReader().lines().reduce(String::concat).orElse("{}");
        request.setAttribute("fooJson", "{\"name\":\"foo\",\"age\":\"30\",\"birthDay\":\"2022-06-08\"}");
    }

}
