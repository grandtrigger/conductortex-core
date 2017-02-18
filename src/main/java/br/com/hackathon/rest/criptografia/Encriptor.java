/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.criptografia;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.bouncycastle.util.encoders.Hex;

/**
 * 
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 */
public class Encriptor implements Serializable{

    @Inject
    private Cipher cipher;
    
    @Inject
    private SecretKeySpec secretKeySpec;

    public Encriptor() {
    }
    
    @Produces
    private Encriptor produces(){
        return new Encriptor();
    }
    
    public String encrypt(String str) {
        String encriptedSenha = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] resultado = cipher.doFinal(str.getBytes("UTF-8"));
            encriptedSenha = new String(Hex.encode(resultado));
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
        }
        
        return encriptedSenha;
    }

    public byte[] decrypt(String str) {
        byte[] senhaPlana = null;
        try {
            byte[] bufferSenha = Hex.decode(str);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            senhaPlana = cipher.doFinal(bufferSenha);
        } catch ( InvalidKeyException | IllegalBlockSizeException | BadPaddingException | StringIndexOutOfBoundsException ex) {
        }
        return senhaPlana;
    }

    public boolean checkPassword(String senhaPlana, String senhaEncriptada) {
        return encrypt(senhaPlana).equals(senhaEncriptada);
    }
    
}
