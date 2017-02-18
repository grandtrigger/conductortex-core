/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.util;

import java.lang.reflect.ParameterizedType;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @param <T>
 * @date 18/02/2017
 *
 */
public class ConversorJSONFabrica<T> {

    @Produces
    private ConversorJSON<T> create(InjectionPoint injectionPoint) {
        ParameterizedType type = (ParameterizedType) injectionPoint.getType();
        Class classes = (Class) type.getActualTypeArguments()[0];
        return new ConversorJSON( classes );
    }
    
}
