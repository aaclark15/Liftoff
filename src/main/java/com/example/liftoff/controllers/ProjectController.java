package com.example.liftoff.controllers;

import com.example.liftoff.forms.Project;
import com.example.liftoff.models.data.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectDao projectDao;

    //renders home page
    @RequestMapping(value="")
    public String index(Model model) {

        model.addAttribute("title", "Select Your Project!");
        model.addAttribute("projects", projectDao.findAll());

        return "project/index";
    }

    //render create project page
    @RequestMapping(value="create", method = RequestMethod.GET)
    public String create(Model model) {

        model.addAttribute("title", "Create a New Project!");
        model.addAttribute(new Project());

        return "project/create";
    }

    //process the create project page
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(Model model, @ModelAttribute @Valid Project project,
                         Errors errors) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Create a New Project!");
            model.addAttribute("project", project);
            return "project/create";
        }
        projectDao.save(project);
        return "redirect:";
    }



}
