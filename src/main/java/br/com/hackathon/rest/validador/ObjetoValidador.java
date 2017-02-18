/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.validador;

import javax.enterprise.inject.Produces;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 17/02/2017
 *
 */
public class ObjetoValidador {

    @Produces
    private ObjetoValidador produces(){
        return new ObjetoValidador();
    }
    
    /**
     * Método que verifica se um Objeto esta nulo. Caso esteja é retornado false e caso contrario é retorado true.
     * @param obj
     * @return Boolean
     */
    public Boolean isValid(Object obj){
        return obj != null;
    }
    
    
}
