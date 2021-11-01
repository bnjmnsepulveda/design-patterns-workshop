package com.witi.dao.pattern;

import com.witi.model.Hero;

import java.util.List;

public interface HeroDAO {

    int save(Hero hero);
    List<Hero> findAll();
    List<Hero> findByUniverse(String universe);

}
