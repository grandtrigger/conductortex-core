/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.dao;

import br.com.hackathon.rest.interfaces.Persistencia;
import br.com.hackathon.rest.model.Conta;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
public class LoginDAO {

    
    private Persistencia<Conta, Long> dao;
    
    public Conta consultarContaPorTelefoneSenha(String telefone, String senha){
        return null;
    }
    
}
