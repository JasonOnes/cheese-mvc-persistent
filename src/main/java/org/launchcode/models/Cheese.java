package org.launchcode.models;

import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Entity
public class Cheese {
//public class Cheese implements CheeseDao {//note for findByCat() functionality


    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @ManyToOne
    private Category category;

    @ManyToMany
    private List<Menu> menus;


    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category cat) {
        this.category = cat;
    }

//    @Override
//    public <cheese extends Cheese> cheese save(cheese entity) {
//        return null;
//    }//note not a final override
//
//    @Override
//    public <cheese extends Cheese> Iterable<cheese> save(Iterable<cheese> entities) {
//        return null;
//    }//note not a final override
//
//    @Override
//    public Cheese findOne(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public boolean exists(Integer integer) {
//        return false;
//    }
//
//    @Override
//    public Iterable<Cheese> findAll() {
//        return null;
//    }
//
//    @Override
//    public Iterable<Cheese> findAll(Iterable<Integer> integers) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void delete(Integer integer) {
//
//    }
//
//    @Override
//    public void delete(Cheese entity) {
//
//    }
//
//    @Override
//    public void delete(Iterable<? extends Cheese> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    //note here?
//
//    private CheeseDao cheeseDao;
//    private CategoryDao categoryDao;
//    Iterable<Cheese> cheeses = cheeseDao.findAll();
//    ArrayList<Cheese> matchCheese = new ArrayList<>();
//
//    @Override
//    public List<Cheese> findByCatId(int catId) {
//        for (Cheese cheese : cheeses)
//            if (cheese.getCategory() == categoryDao.findOne(catId)) {
//                matchCheese.add(cheese);
//            }
//        return matchCheese;
//    }

}
