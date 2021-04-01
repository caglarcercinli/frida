package be.vdab.frida.controllers;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.services.SausService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("sauzen")
public class SausController {
    private final Saus[] sauzen={
            new Saus(1,"cocktail",new String[]{"mayonaise","ketchup","cognac"}),
            new Saus(6, "mayonaise", new String[] {"ei", "mosterd"}),
            new Saus(7, "mosterd", new String[] {"mosterd", "azijn", "witte wijn"}),
            new Saus(12, "tartare", new String[] {"mayonaise", "augurk", "tabasco"}),
            new Saus(44, "vinaigrette", new String[] {"olijfolie","mosterd","azijn"})
    };
    private final char[] alfabet="abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final SausService sausService;

    public SausController(SausService sausService) {
        this.sausService = sausService;
    }

    @GetMapping
    public ModelAndView sauzen(){

        return new ModelAndView("sauzen","sauzen",sausService.findAll());
    }

    @GetMapping("{id}")
    public ModelAndView saus(@PathVariable long id){
    var modelAndView=new ModelAndView("saus");
        sausService.findByID(id).ifPresent(saus -> modelAndView.addObject(saus));
        return modelAndView;
    }
  @GetMapping("alfabet")
    public ModelAndView alfabet(){
        return new ModelAndView("SausAlfabet","alfabet",alfabet);
  }
  private List<Saus> sauzenDieBeginnenMet(char letter){
        return Arrays.stream(sauzen)
                .filter(saus->saus.getNaam().charAt(0)== letter)
                .collect(Collectors.toList());
  }
  @GetMapping("alfabet/{letter}")
    public ModelAndView sauzenBeginnenMet(@PathVariable char letter){
        return new ModelAndView("sausAlfabet","alfabet",alfabet)
                .addObject("sauzen",sausService.findByNaamBeginMet(letter));
  }
}
