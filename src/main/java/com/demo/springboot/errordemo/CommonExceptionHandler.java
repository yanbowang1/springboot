package com.demo.springboot.errordemo;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * spingboot 异常处理示例
 * 异常示例类
 */
@ControllerAdvice
public class CommonExceptionHandler {
    /**
     * 拦截Exception的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(Exception e){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("respCode", "9999");
        result.put("respMsg", e.getMessage());
        return result;
    }
}
