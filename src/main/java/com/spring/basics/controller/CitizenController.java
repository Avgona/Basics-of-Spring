package com.spring.basics.controller;

import com.spring.basics.Service.Interface.CitizenService;
import com.spring.basics.entity.Citizen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CitizenController {

    @Autowired
    CitizenService citizenService;

    //MAIN page
    @GetMapping("/main-table")
    public String mainTable(Model theModel){

        List<Citizen> citizens = citizenService.getCitizens();

        theModel.addAttribute("citizens", citizens);

        return "main-table";
    }


    // Adding new Citizen into database
    @GetMapping("/add-citizen")
    public String addCitizen(Model model){

        Citizen citizen = new Citizen();

        model.addAttribute("citizen", citizen);

        return "add-citizen";
    }

    @PostMapping("/save-citizen")
    public String saveCitizen(@ModelAttribute("citizen") Citizen citizen){

        citizenService.saveCitizen(citizen);

        return "redirect:/main-table";
    }

    @GetMapping("/updateCitizen")
    public String updateCitizen(@ModelAttribute("citizenId") int id,
                                Model model){

        Citizen citizen = citizenService.getCitizen(id);

        model.addAttribute("citizen", citizen);

        return "add-citizen";
    }

    @GetMapping("/deleteCitizen")
    public String deleteCitizen(@ModelAttribute("citizenId") int id){

        citizenService.deleteCitizen(id);

        return "redirect:/main-table";
    }
}
