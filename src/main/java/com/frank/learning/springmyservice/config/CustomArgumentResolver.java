package com.frank.learning.springmyservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frank.learning.springmyservice.annotation.ReqAttr;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class CustomArgumentResolver implements HandlerMethodArgumentResolver {

    private final ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ReqAttr.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        ReqAttr reqAttr = parameter.getParameterAnnotation(ReqAttr.class);
        if (Objects.nonNull(reqAttr)) {
            String json = (String) webRequest.getAttribute(reqAttr.value(), RequestAttributes.SCOPE_REQUEST);
            return objectMapper.readValue(json, parameter.getParameterType());
        }
        return null;
    }
}