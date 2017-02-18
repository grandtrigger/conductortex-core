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
public class StringValidador {

    @Produces
    private StringValidador produces(){
        return new StringValidador();
    }
    
    /**
     * Método que verifica se uma String é nula, vazia ou é espaço em branco.
     * @param str
     * @return Boolean
     */
    public Boolean isValid(String str){
        return str != null && !str.equals("") && !str.trim().isEmpty();
    }
    
}
