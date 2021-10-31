package com.witi.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Hero {
    private String name;
    private String power;
    private String villainEnemy;
}
