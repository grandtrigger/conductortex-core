/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.model;

import java.util.List;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 19/02/2017
 *
 */
public class UsuarioAgendaCelular {

    private List<String> telefones;

    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }
}
