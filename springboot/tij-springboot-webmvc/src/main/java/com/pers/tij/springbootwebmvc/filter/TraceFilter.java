package com.pers.tij.springbootwebmvc.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.UUID;

/**
 * @author zachary
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TraceFilter implements Filter {
    public static final String TRACE_ID = "trace_id";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("request#getContextPath: {}", request.getContextPath());
        log.info("request#getServletPath: {}", request.getServletPath());
        log.info("request#getRequestURI:  {}", request.getRequestURI());

        setTraceId(request);

        chain.doFilter(request, servletResponse);
    }

    private void setTraceId(HttpServletRequest request) {
        String traceId = request.getHeader(TRACE_ID);
        if (!StringUtils.hasText(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        MDC.put(TRACE_ID, traceId);
    }
}
