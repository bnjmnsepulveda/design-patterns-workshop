package com.witi;

import com.witi.dao.implementation.PostgresHeroDAO;
import com.witi.dao.implementation.PostgresVillainDAO;
import com.witi.dao.pattern.HeroDAO;
import com.witi.factory.implementation.PostgresConnectionFactory;
import com.witi.factory.pattern.ConnectionFactory;
import com.witi.model.Hero;
import com.witi.model.Villain;
import com.witi.templatemethod.implementation.HeroSqlTemplate;
import com.witi.templatemethod.implementation.VillainSqlTemplate;


public class App {

    public static void main(String ...args){

        var hero = Hero.builder()
                .name("PythonMan")
                .power("drop code lines")
                .universe("witi")
                .villainEnemy("JavaMan")
                .build();

        var villain = Villain.builder()
                .name("Mr burns")
                .heroEnemy("maggie simpson")
                .universe("simpsons")
                .power("shutdown the sun")
                .build();

        System.out.println("print villain " + villain);

        ConnectionFactory connectionFactory = PostgresConnectionFactory.builder()
                .database("witi")
                .hostname("localhost")
                .pass("witi")
                .user("postgres")
                .build();

        HeroSqlTemplate heroTemplate = new HeroSqlTemplate(connectionFactory);
        HeroDAO heroDAO = new PostgresHeroDAO(heroTemplate);

        var villainTemplate = new VillainSqlTemplate(connectionFactory);
        var villainDAO = new PostgresVillainDAO(villainTemplate);
        var saved = villainDAO.save(villain);
        System.out.println("saved = " + saved);
        var records = villainDAO.findAll();
        records.forEach(System.out::println);


    }
}
