package com.witi.factory.implementation;

import com.witi.dao.implementation.PostgresVillainDAO;
import com.witi.dao.pattern.VillainDAO;
import com.witi.factory.pattern.ConnectionFactory;
import com.witi.factory.pattern.DAOFactory;
import com.witi.templatemethod.implementation.VillainSqlTemplate;

public class VillainDAOFactory implements DAOFactory<VillainDAO> {

    private static VillainDAOFactory INSTANCE;

    private VillainDAOFactory() {}

    public static VillainDAOFactory getInstance() {
        if (INSTANCE==null){
            INSTANCE = new VillainDAOFactory();
        }
        return INSTANCE;
    }

    @Override
    public VillainDAO getDAO() {
        ConnectionFactory connectionFactory = PostgresConnectionFactory.builder()
                .database("witi")
                .hostname("localhost")
                .pass("witi")
                .user("witi")
                .build();
        var villainTemplate = new VillainSqlTemplate(connectionFactory);
        return new PostgresVillainDAO(villainTemplate);
    }
}
