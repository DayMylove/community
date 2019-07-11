package com.av50200264.community.ctroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class hellocontroller {

    @RequestMapping("/greeting")
    public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        model.addAttribute("name",name);
        return "gretting";
    }
}
