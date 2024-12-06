package com.pigeonskyraceSecurity.utils.csvConverters;

import com.opencsv.bean.AbstractBeanField;
import com.pigeonskyraceSecurity.models.Race;

public class RaceConverter extends AbstractBeanField<Race, String> {
    @Override
    protected Race convert(String value) {

        Race race = new Race();
        race.setId(value);
        return race;
    }
}
