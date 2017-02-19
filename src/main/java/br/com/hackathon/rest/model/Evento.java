/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.model;

import br.com.hackathon.rest.enumeracoes.TipoEvento;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
@Entity
@TableGenerator(allocationSize = 1, initialValue = 1, name = "evento_seq")
@NamedQueries(value = {
    @NamedQuery(name = "evento.consultar.eventos.por.participante", query = "SELECT e FROM Evento e, Participante p WHERE e.tipoEvento = :evento AND (e.criador.conta.telefone = :telefone OR p MEMBER OF e.participantes )"),
    @NamedQuery(name = "evento.consultar.eventos.por.criador", query = "SELECT e FROM Evento e WHERE e.criador.conta.telefone = :telefone AND e.tipoEvento = :evento"),
    @NamedQuery(name = "evento.consultar.todos.eventos.atuais", query = "SELECT e FROM Evento e WHERE e.tipoEvento = :evento")
})
public class Evento implements Serializable{

    @Id
    @GeneratedValue(generator = "evento_seq", strategy = GenerationType.TABLE)
    private Long id;
    
    @Column(nullable = false, length = 200, unique = false)
    private String descricao;
    
    @Column(nullable = false, unique = false, precision = 2)
    private BigDecimal valor;
    
    @OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Participante criador;
    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Participante> participantes;

    @Temporal(TemporalType.DATE)
    private Calendar dataCriacao;
    
    @Enumerated(EnumType.STRING)
    private TipoEvento tipoEvento;
    
    public Evento() {
        this.participantes = new ArrayList<>();
    }

    public Evento(String descricao, BigDecimal valor, Participante criador, List<Participante> participantes, TipoEvento evento) {
        this.descricao = descricao;
        this.valor = valor;
        this.criador = criador;
        this.participantes = participantes;
        this.tipoEvento = evento;
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

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
}
