package com.example.liftoff.controllers;


import com.example.liftoff.models.Category;
import com.example.liftoff.models.Item;
import com.example.liftoff.models.Project;
import com.example.liftoff.models.data.CategoryDao;
import com.example.liftoff.models.data.ItemDao;
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
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProjectDao projectDao;


    //renders the add Item page
    @RequestMapping(value="addItem/{catId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int catId) {

        Optional<Category> cat = categoryDao.findById(catId);
        if (cat.isPresent()) {
            Category category = cat.get();

            model.addAttribute("title", "Add an Item to: "+ category.getName());
            model.addAttribute(new Item());
            model.addAttribute("catId", catId);
        }
        return "item/addItem";
    }

    //process the add Item page
    @RequestMapping(value="addItem/{catId}", method = RequestMethod.POST)
    public String addItem(Model model, @RequestParam int catId,
                         @ModelAttribute @Valid Item newItem,
                         Errors errors ) {

        if(errors.hasErrors()) {
            Optional<Category> cat = categoryDao.findById(catId);
            if (cat.isPresent()) {
                Category category = cat.get();
                    model.addAttribute("title", "Add an Item to: "+ category.getName());
                    model.addAttribute("item", newItem);
                    model.addAttribute("catId", catId);}
            return "item/addItem";
        }

        Optional<Category> cat = categoryDao.findById(catId);
        if(cat.isPresent()) {
            newItem.setCategory(cat.get());
            itemDao.save(newItem);
        }
        return "category/summary";
        //return "redirect:/item/compare/" + catId; - change to this when compare form is done
    }

}
