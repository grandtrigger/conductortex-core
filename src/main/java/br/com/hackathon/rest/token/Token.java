/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.token;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 */
public class Token implements Serializable{

    private String email;
    private LocalDateTime geracao;
    private Long duracaoMinutos;

    public Token() {
    }

    public Token(String token, Long duracao) {
        this.email = token;
        this.geracao = LocalDateTime.now();
        this.duracaoMinutos = duracao;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getGeracao() {
        return geracao;
    }

    public Long getDuracaoMinutos() {
        return duracaoMinutos;
    }

    private void setGeracao(LocalDateTime geracao) {
        this.geracao = geracao;
    }
    
    public void setDuracaoMinutos(Long duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
    
    private LocalDateTime validade(){
        return getGeracao().plusMinutes( getDuracaoMinutos() );
    }
    
    public Boolean isExpirado() {
        LocalDateTime localDate = LocalDateTime.now();
        return localDate.isAfter( validade() );
    }
    
    public void atualizar(){
        setGeracao( LocalDateTime.now() );
    }
}
