package com.witi.dao.pattern;

import com.witi.model.Villain;

import java.util.List;

public interface VillainDAO {

    int save(Villain villain);
    Villain findByName(String name);
    List<Villain> findAll();
    List<Villain> findByUniverse(String universe);


}
