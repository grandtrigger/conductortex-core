/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.validador;

import java.util.List;
import java.util.Map;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 17/02/2017
 *
 */
public class ListaValidador {

    @Produces
    private ListaValidador produces(){
        return new ListaValidador();
    }
    
    /**
     * Método que verifica se um Map<?, ?> está válido. Retorna false se e
     * somente se o Map<?, ?> estiver nulo ou vazio, caso contrario retorna true
     *
     * @param map
     * @return Boolean
     */
    public Boolean isValid(Map<?, ?> map) {
        return map != null ? !map.isEmpty() : Boolean.FALSE;
    }
    
    /**
     * Método que verifica se uma List<?> está válido. Retorna false se e
     * somente se a Lis<?> estiver nula ou vazia, caso contrario retorna true
     *
     * @param list
     * @return Boolean
     */
    public Boolean isValid(List<?> list) {
        return list != null ? !list.isEmpty() : Boolean.FALSE;
    }
}
