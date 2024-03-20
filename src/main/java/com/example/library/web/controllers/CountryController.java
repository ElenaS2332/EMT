package com.example.library.web.controllers;

import com.example.library.models.Country;
import com.example.library.services.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String getCountriesPage(Model model){
        List<Country> countries = this.countryService.findAll();
        model.addAttribute("countries", countries);
        model.addAttribute("bodyContent", "countries");
        return "master-template";
    }

    @GetMapping("/add-country")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddCountryPage(Model model) {
        model.addAttribute("bodyContent", "add-country");
        return "master-template";
    }

    @PostMapping("save-country")
    public String addCountry(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String continent){
        this.countryService.save(name, continent);
        return "redirect:/countries";

    }
}
