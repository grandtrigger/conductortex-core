/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.dao;

import br.com.hackathon.rest.interfaces.Persistencia;
import br.com.hackathon.rest.model.Conta;
import javax.inject.Inject;

/**
 *
 * @author Alexandre Feitosa Faustino <afeitosa29@gmail.com>
 * @date 18/02/2017
 *
 */
public class ContaDAO {

    @Inject
    private Persistencia<Conta, Long> dao;
    
    public Conta cadastraConta(Conta conta){
        
        return dao.gravar(conta);
        
    }
}
