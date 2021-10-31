package com.witi.dao.pattern;

import com.witi.model.Hero;

import java.util.List;

public interface HeroDAO {

    List<Hero> findAll();
    List<Hero> findByUniverse(String universe);

}
