package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Menu {

    @NotNull
    @Size(min=1, max=45, message = "a name must be entered")
    String name;

    @Id
    @GeneratedValue
    public int id;

    @ManyToMany
    List<Cheese> cheeses;

    public Menu() {}

    public Menu(String aName) {
        this.name = aName;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }


    public void addItem(Cheese item) {
        cheeses.add(item);
    }



}
