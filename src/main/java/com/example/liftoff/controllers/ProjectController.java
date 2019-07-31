package com.example.liftoff.controllers;

import com.example.liftoff.forms.Category;
import com.example.liftoff.forms.Project;
import com.example.liftoff.models.data.CategoryDao;
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

    @Autowired
    private CategoryDao categoryDao;


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

    //renders summary page
    @RequestMapping(value="summary", method = RequestMethod.GET)
    public String summary(Model model) {

        model.addAttribute("title", "Project Summary");
        model.addAttribute("categories", categoryDao.findAll());

        return "project/summary";
    }

    //renders addCat page
    @RequestMapping(value="addCat", method = RequestMethod.GET)
    public String addCat(Model model) {

        model.addAttribute("title", "Add Item to Shop For!");
        model.addAttribute(new Category());

        return "project/addCat";
    }

    //process the add Category page
    @RequestMapping(value="addCat", method = RequestMethod.POST)
    public String addCat(Model model, @ModelAttribute @Valid Category category,
                         Errors errors) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Item to Shop For!");
            model.addAttribute("category", category);
            return "project/addCat";
        }

        categoryDao.save(category);
        return "redirect:/project/summary";
    }

}
