/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
@Entity
@TableGenerator(initialValue = 1, allocationSize = 1, name = "participante_seq")
public class Participante implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "participante_seq")
    private Long id;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Conta conta;
    
    @Column(length = 1, nullable = false)
    private Boolean confirmacao;

    public Participante() {
    }

    public Participante(Conta conta, Boolean confirmacao) {
        this.conta = conta;
        this.confirmacao = confirmacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Boolean getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(Boolean confirmacao) {
        this.confirmacao = confirmacao;
    }
    
}
