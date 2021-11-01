package com.witi.dao.implementation;

import com.witi.dao.pattern.HeroDAO;
import com.witi.model.Hero;
import com.witi.templatemethod.implementation.HeroSqlTemplate;

import java.util.List;

public class PostgresHeroDAO implements HeroDAO {

    private HeroSqlTemplate template;

    public PostgresHeroDAO(HeroSqlTemplate template) {
        this.template = template;
    }

    @Override
    public int save(Hero hero) {
        var insert = "INSERT INTO heroes (\"name\", power, universe, villain_enemy) VALUES(?, ?, ?, ?)";
        return template.update(
                insert,
                hero.getName(),
                hero.getPower(),
                hero.getUniverse(),
                hero.getVillainEnemy()
        );
    }

    @Override
    public List<Hero> findAll() {
        return template.selectMany("SELECT * FROM heroes");
    }

    @Override
    public List<Hero> findByUniverse(String universe) {
        return template.selectMany("SELECT * FROM heroes WHERE universe = ?", universe);
    }
}
