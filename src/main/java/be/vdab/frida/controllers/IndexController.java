package be.vdab.frida.controllers;

import be.vdab.frida.domain.Adres;
import be.vdab.frida.domain.Gemeente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/")

public class IndexController {
    private static final int EEN_JAAR_IN_SECONDEN = 31_536_000;
    @GetMapping
    public ModelAndView index(@CookieValue Optional<Integer> aantalBezoeken, HttpServletResponse response){
        var dag= LocalDate.now().getDayOfWeek() ==DayOfWeek.MONDAY ? "gesloten" : "open";
        var modelAndView = new ModelAndView("index","dag",dag);
        modelAndView.addObject("adres",
                new Adres("Haarstraat","3",
                        new Gemeente("Wilrijk",2160)));
        var nieuwAantalBezoeken = aantalBezoeken.orElse(0) + 1;
        var cookie = new Cookie("aantalBezoeken", String.valueOf(nieuwAantalBezoeken));
        cookie.setMaxAge(31_536_000);
        cookie.setPath("/");
        response.addCookie(cookie);
        modelAndView.addObject("aantalBezoeken", nieuwAantalBezoeken);
        return modelAndView;
    }
}
