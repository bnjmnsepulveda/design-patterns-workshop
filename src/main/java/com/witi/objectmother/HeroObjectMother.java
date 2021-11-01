package com.witi.objectmother;

import com.witi.model.Hero;

public class HeroObjectMother {

    private static Hero.HeroBuilder buildBase(){
        return Hero.builder()
                .name("Goku")
                .power("kame hame ha")
                .universe("dragonball")
                .villainEnemy("Frezzer");
    }

    public static Hero withName(String name) {
        return buildBase()
                .name(name)
                .build();
    }

    public static Hero withEnemy(String enemy) {
        return buildBase()
                .villainEnemy(enemy)
                .build();
    }

    public static Hero withPower(String power) {
        return buildBase()
                .power(power)
                .build();
    }

    public static Hero withNameAndPower(String name, String power) {
        return buildBase()
                .name(name)
                .power(power)
                .build();
    }

    public static Hero withNamePowerAndEnemy(String name, String power, String enemy) {
        return buildBase()
                .name(name)
                .power(power)
                .villainEnemy(enemy)
                .build();
    }
}
