package com.interceptors.demo.springboot3_int.interceptors;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("loadingTimeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception 
    {
        Boolean eval = true;
        long start = System.currentTimeMillis();
        request.setAttribute("start", start);

        delay();
        var message = MessageFormat.format("LoadingTimeInterceptor: preHandle() {0} at {1} miliseconds",
                ((HandlerMethod) handler).getMethod().getName(), start);
        logger.info(message);

        String method = ((HandlerMethod) handler).getMethod().getName();
        if (method.equals("getBeta")) {
            Map<String, String> jsonResp = new HashMap<>();
            jsonResp.put("error", "Access not allowed");

            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(jsonResp);

            response.setContentType("application/json");
            response.setStatus(401);
            response.getWriter().write(jsonString);

            eval = false;

        }

        return eval;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView)
    throws Exception 
    {
        long end = System.currentTimeMillis();
        long start = (long) request.getAttribute("start");

        var message = MessageFormat.format("LoadingTimeInterceptor: postHandle() out {0} at {1} miliseconds",
                ((HandlerMethod) handler).getMethod().getName(), end);
        logger.info(message);

        var message2 = MessageFormat.format("LoadingTimeInterceptor: Total time {0} miliseconds", (end - start));
        logger.warn(message2);
    }

    private void delay() throws InterruptedException {
        Random random = new Random();
        int delay = random.nextInt(2000);
        Thread.sleep(delay);
    }

}
