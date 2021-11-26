package com.witi;

import com.witi.dao.implementation.PostgresHeroDAO;
import com.witi.dao.pattern.HeroDAO;
import com.witi.factory.implementation.PostgresConnectionFactory;
import com.witi.factory.implementation.VIllainDAOFactory;
import com.witi.factory.pattern.ConnectionFactory;
import com.witi.objectmother.HeroObjectMother;
import com.witi.templatemethod.implementation.HeroSqlTemplate;


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

        var goku = HeroObjectMother.withName("Goku ultrainstinto");
        var gohan = HeroObjectMother.withName("gohan");
        var vegeta = HeroObjectMother.withNamePowerAndEnemy("vegeta","Ozaru", "goku");

        System.out.println("\n --- Initial Heroes List ---- \n");
        heroDAO.findAll()
                .forEach(h -> System.out.println(h.getName()));

        heroDAO.save(goku);
        heroDAO.save(gohan);
        heroDAO.save(vegeta);

        System.out.println("\n --- Heroes List with dragonball universe ---- \n");

        heroDAO.findAll()
                .forEach(System.out::println);

        var villainDAO = VIllainDAOFactory.getInstance();
        villainDAO.getDAO()
                .findAll()
                .forEach(x -> System.out.println(x));



    }

}
