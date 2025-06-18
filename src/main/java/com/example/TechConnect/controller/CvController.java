package com.example.TechConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.TechConnect.model.CurriculumVitae;
import com.example.TechConnect.service.CvService;

import jakarta.validation.Valid;

@Controller
public class CvController {

    @Autowired
    private CvService cvService;

    @GetMapping("/candidate/cv/create")
    public String CreateCv(WebRequest request, Model model) {
        CurriculumVitae cv = new CurriculumVitae();
        model.addAttribute("add", cv);
        return "candidate/cv/create";
    }

    @PostMapping("/candidate/cv/create")
    public String Create_Cv(@Valid @ModelAttribute("add") CurriculumVitae curriculumVitae, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "candidate/cv/create";
        }

        cvService.addCv(curriculumVitae);
        return "redirect:/candidate/cv/list";
    }

    @GetMapping("/candidate/cv/list")
    public String List_Cv(Model model) {
        model.addAttribute("cvs", cvService.getAllCv());
        return "candidate/cv/list";
    }

}