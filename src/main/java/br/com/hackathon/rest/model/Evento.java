/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
@Entity
@TableGenerator(allocationSize = 1, initialValue = 1, name = "evento_seq")
public class Evento implements Serializable{

    @Id
    @GeneratedValue(generator = "evento_seq", strategy = GenerationType.TABLE)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    @OneToOne(cascade = {CascadeType.MERGE})
    private Participante criador;
    @OneToMany(cascade = {CascadeType.MERGE})
    private List<Participante> participantes;

    public Evento() {
        this.participantes = new ArrayList<>();
    }

    public Evento(String descricao, BigDecimal valor, Participante criador, List<Participante> participantes) {
        this.descricao = descricao;
        this.valor = valor;
        this.criador = criador;
        this.participantes = participantes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Participante getCriador() {
        return criador;
    }

    public void setCriador(Participante criador) {
        this.criador = criador;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }
    
}
