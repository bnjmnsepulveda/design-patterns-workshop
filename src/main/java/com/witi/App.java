package com.witi;

import com.witi.dao.implementation.PostgresHeroDAO;
import com.witi.dao.pattern.HeroDAO;
import com.witi.factory.implementation.PostgresConnectionFactory;
import com.witi.factory.pattern.ConnectionFactory;
import com.witi.model.Hero;
import com.witi.model.Villain;
import com.witi.templatemethod.implementation.HeroSqlTemplate;

import java.util.List;

public class App {
    public static void main(String ...args){

        var hero = Hero.builder()
                .name("PythonMan")
                .power("drop code lines")
                .universe("witi")
                .villainEnemy("JavaMan")
                .build();

        var villain = Villain.builder()
                .name("JavaMan")
                .heroEnemy("pythonMan")
                .universe("witi")
                .power("write 1000 package names at the speed of the light")
                .build();
        System.out.println(" print villain " + villain);


        ConnectionFactory connectionFactory = PostgresConnectionFactory.builder()
                .database("witi")
                .hostname("localhost")
                .pass("witi")
                .user("witi")
                .build();
        /**
         * INCORRECT
         */
        HeroSqlTemplate heroTemplate = new HeroSqlTemplate(connectionFactory);
        HeroDAO heroDAO = new PostgresHeroDAO(heroTemplate);
        // sin dao
        List<Hero> heroes = heroTemplate.selectMany("SELECT * FROM heroes");
        // con dao
        List<Hero> AllHeroes =heroDAO.findAll();
        List<Hero> heroesByUniverse =heroDAO.findByUniverse("marvel");


    }
}
