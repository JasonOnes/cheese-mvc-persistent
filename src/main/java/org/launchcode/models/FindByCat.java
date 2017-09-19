package org.launchcode.models;

import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.CheeseDao;

import java.util.ArrayList;
import java.util.List;

//class FindByCat implements CheeseDao {
//    Iterable<Cheese> cheeses = CheeseDao.findAll();
//    ArrayList<Cheese> matchCheese = new ArrayList<>();
//
//    @Override
//    public List<Cheese> findByCatId(int catId) {
//        for (Cheese cheese : cheeses)
//            if (cheese.getCategory() == CategoryDao.findOne(catId)) {
//                matchCheese.add(cheese);
//            }
//        return matchCheese;
//    }
//
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
//}
