package com.witi;

import com.witi.dao.implementation.PostgresHeroDAO;
import com.witi.dao.implementation.PostgresVillainDAO;
import com.witi.dao.pattern.HeroDAO;
import com.witi.factory.implementation.PostgresConnectionFactory;
import com.witi.factory.pattern.ConnectionFactory;
import com.witi.model.Hero;
import com.witi.model.Villain;
import com.witi.objectmother.HeroObjectMother;
import com.witi.templatemethod.implementation.HeroSqlTemplate;
import com.witi.templatemethod.implementation.VillainSqlTemplate;


public class App {

    public static void main(String ...args){

        ConnectionFactory connectionFactory = PostgresConnectionFactory.builder()
                .database("witi")
                .hostname("localhost")
                .pass("witi")
                .user("witi")
                .build();

        // hero DAO
        HeroSqlTemplate heroTemplate = new HeroSqlTemplate(connectionFactory);
        HeroDAO heroDAO = new PostgresHeroDAO(heroTemplate);
        // villain DAO
        var villainTemplate = new VillainSqlTemplate(connectionFactory);
        var villainDAO = new PostgresVillainDAO(villainTemplate);

        var goku = HeroObjectMother.withName("Goku ultrainstinto");
        var gohan = HeroObjectMother.withName("gohan");
        var vegeta = HeroObjectMother.withNamePowerAndEnemy("vegeta","Ozaru", "goku");

        heroDAO.save(goku);
        heroDAO.save(gohan);
        heroDAO.save(vegeta);

        heroDAO.findAll()
                .forEach(System.out::println);


        var villain = Villain.builder()
                .name("Mr burns")
                .heroEnemy("maggie simpson")
                .universe("simpsons")
                .power("shutdown the sun")
                .build();

        var saved = villainDAO.save(villain);
        System.out.println("saved = " + saved);
        var records = villainDAO.findAll();
        records.forEach(System.out::println);


    }
}
