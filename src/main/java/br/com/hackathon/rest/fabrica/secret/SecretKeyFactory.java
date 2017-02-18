/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.fabrica.secret;

import br.com.hackathon.rest.fabrica.key.Key;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 16/02/2017
 *
 */
public class SecretKeyFactory {

    @Inject
    private Key key;
    
    @Produces
    private SecretKeySpec produces() {
        try {
            return new SecretKeySpec( key.getKey(), "AES");
        } catch ( NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            return new SecretKeySpec("default".getBytes(), "AES");
        }
    }
    
}
