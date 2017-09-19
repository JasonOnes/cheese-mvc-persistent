package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, @RequestParam int categoryId, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "category")
    public String displayCategories(Model model) {

        model.addAttribute("title", "Select a category:");
        model.addAttribute("categories", categoryDao.findAll());

        return "cheese/category";
    }


    @RequestMapping(value = "category/{catId}", method = RequestMethod.GET)
    public String displayCheeseByCategory(Model model, @PathVariable Integer catId) {
        Category cat = categoryDao.findOne(catId);
        Iterable<Cheese> cheeses = cheeseDao.findAll();
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", cat.name + "cheeses");
        //note model.addAttribute("cheeses", cheeseDao.findByCatId(catId));

        return "cheese/index";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese freshCheese = cheeseDao.findOne(cheeseId);//note replaces findById() ?
        Iterable<Category> categories = categoryDao.findAll();
        model.addAttribute("cheesey", freshCheese);
        model.addAttribute("cheeseTypes", categories);

        return "cheese/edit";

    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid Cheese freshCheese, Errors errors,
                                  @RequestParam String name, String description, int cheeseId, int catId) {
                                  //@PathVariable int cheeseId) {

        if (errors.hasErrors()) {

            Iterable<Category> categories = categoryDao.findAll();
            model.addAttribute("title", "edit cheese");
            model.addAttribute("cheesey", freshCheese);
            model.addAttribute("cheeseTypes", categories);
            return "cheese/edit";
        } else {
            Cheese oldCheese = cheeseDao.findOne(cheeseId);
            Category someCat = categoryDao.findOne(catId);
            oldCheese.setName(name);
            oldCheese.setDescription(description);
            oldCheese.setCategory(someCat);
            cheeseDao.save(oldCheese);

            return "redirect:/cheese";
        }


    }
}
