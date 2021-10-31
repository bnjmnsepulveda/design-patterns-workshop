package com.witi.templatemethod.implementation;

import com.witi.factory.pattern.ConnectionFactory;
import com.witi.model.Villain;
import com.witi.templatemethod.pattern.SqlTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VillainSqlTemplate extends SqlTemplate<Villain> {

    public VillainSqlTemplate(ConnectionFactory connection) {
        super(connection);
    }

    @Override
    protected Villain adapterEntity(ResultSet rs) throws SQLException {
        return Villain.builder()
                .name(rs.getString("name"))
                .power(rs.getString("power"))
                .universe(rs.getString("universe"))
                .heroEnemy(rs.getString("hero_enemy"))
                .build();
    }
}
