/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.fabrica.crifra;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 16/02/2017
 *
 */
public class CipherFactory {

    @Produces
    private Cipher produces() throws NoSuchAlgorithmException, NoSuchPaddingException{
        return Cipher.getInstance("AES");
    }
    
}
