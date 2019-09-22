package com.muskopf.tacotuesday.domain;

import com.muskopf.tacotuesday.config.TacoTuesdayApiConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CabezaTaco extends Taco {
    @Autowired
    public CabezaTaco(TacoTuesdayApiConfiguration configuration) {
        this.price = configuration.getCabezaPrice();
    }
}
