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
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
@Entity
@TableGenerator(allocationSize = 1, initialValue = 1, name = "conta_seq")
public class Conta implements Serializable, Comparable<Conta>{

    @Id
    @GeneratedValue(generator = "conta_seq", strategy = GenerationType.TABLE)
    private Long id;
    
    @NotNull(message = "O campo nomeCompleto não pode ser nulo")
    @NotEmpty(message = "O campo nomeCompleto não pode ser vazio")
    @Column(nullable = false, length = 200, unique = false)
    private String nomeCompleto;
    
    @NotNull(message = "O campo apelido não pode ser nulo")
    @NotEmpty(message = "O campo apelido não pode ser vazio")
    @Column(nullable = false, length = 20, unique = false)
    private String apelido;
    
    @NotNull(message = "O campo email não pode ser nulo")
    @NotEmpty(message = "O campo email não pode ser vazio")
    @Email(message = "Email não é válido")
    @Column(nullable = false, length = 250, unique = true)
    private String email;
    
    @NotNull(message = "O campo cpf não pode ser nulo")
    @NotEmpty(message = "O campo cpf não pode ser vazio")
    @Column(nullable = false, length = 11, unique = true)
    private String cpf;
    
    @Column(nullable = false, unique = false)
    private BigDecimal saldo;
    
    @Column(nullable = false, unique = false)
    private BigDecimal limiteEspecial;

    public Conta() {
    }

    public Conta(String nomeCompleto, String apelido, String email, String cpf) {
        this.nomeCompleto = nomeCompleto;
        this.apelido = apelido;
        this.email = email;
        this.cpf = cpf;
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
    
    @Override
    public int compareTo(Conta o) {
        return getNomeCompleto().compareTo(o.getNomeCompleto());
    }
    
}

