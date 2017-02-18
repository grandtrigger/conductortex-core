/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.fabrica.key;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 17/02/2017
 *
 */
public class Key implements Serializable{

    public Key(){}
    
    @Produces
    private Key produces(){
        return new Key();
    }
    
    public byte[] getKey() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String redenetKey = "U(*T661fp8";
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(redenetKey.getBytes("UTF-8"));
    }
    
}
