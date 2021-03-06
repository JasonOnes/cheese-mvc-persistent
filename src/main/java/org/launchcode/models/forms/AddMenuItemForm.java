package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;


public class AddMenuItemForm {

    private Menu menu;
    private Iterable<Cheese> cheeses;



    @NotNull
    //note this annotation is hibernate not javax, not importable at the moment @Positive, unnecessary anyway as autogenerated
    private int menuId;


    @NotNull
    private int cheeseId;

    public AddMenuItemForm() {}

    public AddMenuItemForm(Menu aMenu, Iterable<Cheese> someCheeses) {
        this.menu = aMenu;
        this.cheeses = someCheeses;
    }

    public Menu getMenu() {
        return menu;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public int getCheeseId() {
        return cheeseId;
    }
    public int getMenuId() {
        return menuId;
    }


}

