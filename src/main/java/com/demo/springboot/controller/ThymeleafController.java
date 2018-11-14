package com.demo.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: wangyanbo
 * @Date: 2018/11/5
 * @Description:
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @GetMapping("/map")
    public String index(String name, ModelMap map) {
        map.addAttribute("name", name);
        map.addAttribute("from", "wangyanbotest");
        return "thymeleaf";
    }

    @GetMapping("/mv")
    public ModelAndView index(String name, ModelAndView mv) {
        mv.addObject("name", name);
        mv.addObject("from", "wangyanbotest");
        mv.setViewName("thymeleaf");
        return mv;
    }

    @GetMapping("/")
    public ModelAndView indexWebJars(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
