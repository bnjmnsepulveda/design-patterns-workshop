package com.witi;

import com.witi.model.Hero;
import com.witi.model.Villain;

public class App {
    public static void main(String ...args){
        var hero = Hero.builder()
                .name("PythonMan")
                .power("drop code lines")
                .villainEnemy("JavaMan")
                .build();

        var villain = Villain.builder()
                .name("JavaMan")
                .heroEnemy("pythonMan")
                .power("write 1000 package names at the speed of the light")
                .build();
    }
}
