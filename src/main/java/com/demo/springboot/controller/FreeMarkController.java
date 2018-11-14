package com.demo.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: user
 * @Date: 2018/11/2
 * @Description:
 */
@Controller
@RequestMapping("/freemarker")
public class FreeMarkController {
    @GetMapping("/map")
    public String index(String name, ModelMap map) {
        map.addAttribute("name", name);
        map.addAttribute("from","lqdev.cn");
        return "freemarker";
    }

    @GetMapping("/mv")
    public String index(String name, ModelAndView mv){
        mv.addObject("name", name);
        mv.addObject("from", "lqdev.cn");
        return "freemarker";
    }
}
