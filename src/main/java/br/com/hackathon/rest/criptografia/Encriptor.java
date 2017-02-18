/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.criptografia;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.util.encoders.Hex;

/**
 * 
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 */
public class Encriptor implements Serializable{

    private Cipher cipher;
    private SecretKeySpec secretKeySpec;

    public Encriptor() {
    }
    
    public String encrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException {
        String encriptedSenha = null;
        try {
            cipher = Cipher.getInstance("AES");
            secretKeySpec = new SecretKeySpec( getKey(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] resultado = cipher.doFinal(str.getBytes("UTF-8"));
            encriptedSenha = new String(Hex.encode(resultado));
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
        }
        
        return encriptedSenha;
    }

    public byte[] decrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException {
        byte[] senhaPlana = null;
        try {
            byte[] bufferSenha = Hex.decode(str);
            cipher = Cipher.getInstance("AES");
            secretKeySpec = new SecretKeySpec( getKey(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            senhaPlana = cipher.doFinal(bufferSenha);
        } catch ( UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | StringIndexOutOfBoundsException ex) {
        }
        return senhaPlana;
    }

    public boolean checkPassword(String senhaPlana, String senhaEncriptada) throws NoSuchAlgorithmException, NoSuchPaddingException {
        return encrypt(senhaPlana).equals(senhaEncriptada);
    }
    
    private byte[] getKey() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String redenetKey = "U(*T661fp8";
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(redenetKey.getBytes("UTF-8"));
    }
    
}
