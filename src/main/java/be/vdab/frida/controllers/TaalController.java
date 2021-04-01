package be.vdab.frida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("taal")
public class TaalController {
    @GetMapping
    public ModelAndView taal(@RequestHeader("Accept-Language") String acceptLanguage){
        var modelAndView = new ModelAndView("taal","nederlands",acceptLanguage.startsWith("nl"));
        return modelAndView;
    }
}
