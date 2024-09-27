package com.interface21.webmvc.servlet.mvc.tobe;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class HandlerMappingRegistry {

    private final List<HandlerMapping> handlerMappings = new ArrayList<>();

    public void addHandlerMapping(HandlerMapping handlerMapping) {
        handlerMapping.initialize();
        handlerMappings.add(handlerMapping);
    }

    public Object getHandler(HttpServletRequest request) {
        try {
            return handlerMappings.stream()
                    .map(handlerMapping -> handlerMapping.getHandler(request))
                    .findAny()
                    .orElseThrow(() -> new NullPointerException(
                            String.format("Can not find proper handler from requestUrl: %s, requestMethod: %s", request.getRequestURI(), request.getMethod())));
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
