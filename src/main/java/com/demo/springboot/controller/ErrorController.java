package com.demo.springboot.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: user
 * @Date: 2018/11/2
 * @Description:
 */
@RestController
public class ErrorController {

    @ExceptionHandler(value=Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request,
                                            Exception e){
        ModelAndView mv = new ModelAndView("defaultErrorName");
        mv.addObject("exception", e);
        mv.addObject("url", request.getRequestURL());
        return mv;
    }
}
