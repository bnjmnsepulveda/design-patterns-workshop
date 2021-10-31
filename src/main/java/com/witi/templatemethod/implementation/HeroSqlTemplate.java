package com.witi.templatemethod.implementation;

import com.witi.factory.pattern.ConnectionFactory;
import com.witi.model.Hero;
import com.witi.templatemethod.pattern.SqlTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HeroSqlTemplate extends SqlTemplate<Hero> {

    public HeroSqlTemplate(ConnectionFactory connection) {
        super(connection);
    }

    @Override
    protected Hero adapterEntity(ResultSet rs) throws SQLException {
        return Hero.builder()
                .name(rs.getString("name"))
                .power(rs.getString("power"))
                .villainEnemy(rs.getString("villainEnemy"))
                .build();
    }
}
