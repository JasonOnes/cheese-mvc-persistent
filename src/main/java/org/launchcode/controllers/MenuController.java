package org.launchcode.controllers;


import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value="menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;
    //note need to @Autowire each seperately

    @RequestMapping(value = "")
    public String index(Model model) {
        Iterable<Menu> menuItems = menuDao.findAll();
        model.addAttribute("menus", menuItems);
        model.addAttribute("title", "Menus, Menus, Menus");
        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddForm(Model model) {
        model.addAttribute(new Menu());
        model.addAttribute("title", "Create a Menu!");
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(Model model, @ModelAttribute @Valid Menu newMenu, Errors error) {

        if (error.hasErrors()) {
            model.addAttribute("title", "Try to add a menu again!");
            model.addAttribute(new Menu());
            return "menu/add";
        }

        menuDao.save(newMenu);

        return "redirect:view/" + newMenu.getId();
        //return "view/" + newMenu.getId();
    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId) {
        Menu someMenu = menuDao.findOne(menuId);
        model.addAttribute("menu", someMenu);
        //return "redirect: menu/view/" + menuId;
        return "menu/view";
    }

    @RequestMapping(value = "view", method = RequestMethod.POST)
    public String viewMenu(Model model, @ModelAttribute @Valid Menu aMenu, Errors error) {
        //Menu someMenu = menuDao.findOne(someMenu);
        model.addAttribute("menu", aMenu);
        //return "redirect: menu/view/" + menuId;
        return "redirect:/menu/view/" + aMenu.getId();
       // return "menu/view";
    }

    @RequestMapping(value = "/add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int menuId) {
        Menu someMenu = menuDao.findOne(menuId);
        Iterable<Cheese> cheeses = cheeseDao.findAll(); //note this gives all the cheeses
        AddMenuItemForm someForm = new AddMenuItemForm(someMenu, cheeses);

        model.addAttribute("form", someForm);
        model.addAttribute("title", "Add item to menu: " + someMenu.getName());
        model.addAttribute("menu", someMenu);
        model.addAttribute("cheeses", cheeses);
;
        return "menu/add-item";// + someMenu.getId();
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String processAddedItem(Model model, @ModelAttribute @Valid AddMenuItemForm aForm, Errors error) {
        if (error.hasErrors()) {
            model.addAttribute("title", "Try again!");
            model.addAttribute("form", aForm);
            return "menu/add-item/";// + menuId;
        }

        Menu someMenu = menuDao.findOne(aForm.getMenuId());
        Cheese someCheese = cheeseDao.findOne(aForm.getCheeseId());

        someMenu.addItem(someCheese);
        menuDao.save(someMenu);

        return "redirect:/menu/view/" + someMenu.getId();
    }

}
