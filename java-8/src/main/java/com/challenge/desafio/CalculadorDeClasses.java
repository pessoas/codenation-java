package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel{

    @Override
    public BigDecimal somar(Object object) {
        BigDecimal resul = BigDecimal.ZERO;

        Field[] fields = object.getClass().getDeclaredFields();

        for(Field field: fields) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(Somar.class) && field.getType().equals(BigDecimal.class)) {
                try {
                    resul = resul.add((BigDecimal) field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return resul;
    }

    @Override
    public BigDecimal subtrair(Object object) {
        BigDecimal resul = BigDecimal.ZERO;

        Field[] fields = object.getClass().getDeclaredFields();

        for(Field field: fields) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(Subtrair.class) && field.getType().equals(BigDecimal.class)) {
                try {
                    resul = resul.add((BigDecimal) field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return resul;
    }

    @Override
    public BigDecimal totalizar(Object object) {
        return somar(object).subtract(subtrair(object));
    }
}
