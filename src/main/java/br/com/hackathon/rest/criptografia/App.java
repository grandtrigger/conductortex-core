/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.criptografia;

import javax.inject.Inject;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
public class App {

    public static void main(String[] args) {
        
        Encriptor e = new Encriptor();
        System.out.println( new String(e.decrypt("46f5f7199a3a60e719938b84d3d92118")) );
        
        
    }
    
}
