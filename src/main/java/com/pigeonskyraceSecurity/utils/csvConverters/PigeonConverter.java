package com.pigeonskyraceSecurity.utils.csvConverters;

import com.opencsv.bean.AbstractBeanField;

import com.pigeonskyraceSecurity.models.Pigeon;
import com.pigeonskyraceSecurity.utils.enums.Gender;

import java.util.UUID;

public class PigeonConverter extends AbstractBeanField<Pigeon, String> {


    @Override
    protected Pigeon convert(String value) {

        Pigeon fakePigeon = new Pigeon();
        fakePigeon.setId(UUID.fromString(value));
        fakePigeon.setBandNumber("F" + Math.random() * 10000);
        fakePigeon.setGender(Gender.MALE);
        fakePigeon.setBirthYear("2020");
        fakePigeon.setColor("Gray");

        return fakePigeon;
    }
}
