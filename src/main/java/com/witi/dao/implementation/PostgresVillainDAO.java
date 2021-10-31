package com.witi.dao.implementation;

import com.witi.dao.pattern.VillainDAO;
import com.witi.model.Villain;
import com.witi.templatemethod.implementation.VillainSqlTemplate;

import java.util.List;

public class PostgresVillainDAO implements VillainDAO {

    private VillainSqlTemplate template;

    public PostgresVillainDAO(VillainSqlTemplate template) {
        this.template = template;
    }

    @Override
    public int save(Villain villain) {
        var insert = "INSERT INTO villain (\"name\", power, universe, hero_enemy) VALUES(?, ?, ?, ?)";
        return template.update(
                insert,
                villain.getName(),
                villain.getPower(),
                villain.getUniverse(),
                villain.getHeroEnemy()
        );
    }

    @Override
    public Villain findByName(String name) {
        return template.selectOne("SELECT * FROM villains WHERE name = ?", name);
    }

    @Override
    public List<Villain> findAll() {
        return template.selectMany("SELECT * FROM villain");
    }

    @Override
    public List<Villain> findByUniverse(String universe) {
        return template.selectMany("SELECT * FROM heroes WHERE villain = ?", universe);
    }
}
