/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.util;

import br.com.hackathon.rest.enumeracoes.MensagensCodigo;
import java.util.ResourceBundle;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
public class MensagensBase {

    private final ResourceBundle bundle;
    
    public MensagensBase(){
        this.bundle = ResourceBundle.getBundle("mensagens");
    }
    
    public String get(MensagensCodigo codigo){
        return this.bundle.getString( codigo.name() );
    }
    
    @Produces
    private MensagensBase produces(){
        return new MensagensBase();
    }
}
