package com.example.liftoff.controllers;


import com.example.liftoff.models.Category;
import com.example.liftoff.models.Project;
import com.example.liftoff.models.data.CategoryDao;
import com.example.liftoff.models.data.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private CategoryDao categoryDao;


    //renders summary page
    @RequestMapping(value="summary/{projectId}", method = RequestMethod.GET)
    public String summary(Model model, @PathVariable int projectId) {

        Optional<Project> proj = projectDao.findById(projectId);
        if(proj.isPresent()) {
            Project project = proj.get();
            List<Category> cats = project.getCategory();
            model.addAttribute("categories", cats);
            model.addAttribute("title", "Project Summary: " + project.getName());
            model.addAttribute("projectId", projectId);
        }

        return "category/summary";
    }

    //renders add Category page
    @RequestMapping(value="addCat/{projectId}", method = RequestMethod.GET)
    public String addCat(Model model, @PathVariable int projectId) {

        model.addAttribute("title", "Add Item to Shop For!");
        model.addAttribute(new Category());
        model.addAttribute("projectId", projectId);

        return "category/addCat";
    }

    //process the add Category page
    @RequestMapping(value="addCat/{projectId}", method = RequestMethod.POST)
    public String addCat(Model model, @RequestParam int projectId,
                         @ModelAttribute @Valid Category newCat,
                         Errors errors ) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Item to Shop For!");
            model.addAttribute("category", newCat);
            return "category/addCat";
        }

        Optional<Project> proj = projectDao.findById(projectId);
        if(proj.isPresent()) {
            newCat.setProject(proj.get());
            categoryDao.save(newCat);
        }
        return "redirect:/category/summary/" + projectId;
    }

}
