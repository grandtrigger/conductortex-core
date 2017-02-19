/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
@Entity
@TableGenerator(allocationSize = 1, initialValue = 1, name = "conta_seq")
@NamedQueries(value = {
    @NamedQuery(name = "conta.consultar.conta.por.login.senha", query = "SELECT c FROM Conta c WHERE c.telefone = :telefone AND c.senha = :senha"),
    @NamedQuery(name="conta.consultar.conta.por.telefone", query="SELECT c FROM Conta c WHERE c.telefone = :telefone"),
    @NamedQuery(name="conta.consultar.conta.por.cpf", query="SELECT c FROM Conta c WHERE c.cpf = :cpf"),
    @NamedQuery(name="conta.consultar.conta.por.email", query="SELECT c FROM Conta c WHERE c.email = :email"),
    @NamedQuery(name="conta.consultar.contas.relacionadas.por.telefone", query="SELECT c FROM Conta c WHERE c.telefone IN(:telefones)")
})
public class Conta implements Serializable, Comparable<Conta>{

    @Id
    @GeneratedValue(generator = "conta_seq", strategy = GenerationType.TABLE)
    private Long id;
    
    @Column(nullable = false, length = 200, unique = false)
    private String nomeCompleto;
    
    @Column(nullable = false, length = 11, unique = true)
    private String telefone;
    
    @Column(nullable = false, length = 20, unique = false)
    private String apelido;
    
    @Column(nullable = false, length = 250, unique = true)
    private String email;
    
    @Column(nullable = false, length = 11, unique = true)
    private String cpf;
    
    @Column(nullable = false, unique = false)
    private String senha;
    
    private String registroId;
    
    private BigDecimal saldo;
    
    private BigDecimal limiteEspecial;

    public Conta() {
        this.limiteEspecial = new BigDecimal("100");
    }

    public Conta(String nomeCompleto, String telefone, String apelido, String email, String cpf, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.apelido = apelido;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.limiteEspecial = new BigDecimal("100");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getLimiteEspecial() {
        return limiteEspecial;
    }

    public void setLimiteEspecial(BigDecimal limiteEspecial) {
        this.limiteEspecial = limiteEspecial;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRegistroId() {
        return registroId;
    }

    public void setRegistroId(String registroId) {
        this.registroId = registroId;
    }
    
    @Override
    public int compareTo(Conta o) {
        return getNomeCompleto().compareTo(o.getNomeCompleto());
    }
    
}

