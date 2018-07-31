package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping(value = {""})
    public String getDashboard() {
        return "/dashboard";
    }

    @GetMapping(value = {"/index"})
    public String getIndex() {
        return "/index";
    }
}
