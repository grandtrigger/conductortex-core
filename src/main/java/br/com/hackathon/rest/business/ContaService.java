/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.business;

import br.com.hackathon.rest.dao.LoginDAO;
import br.com.hackathon.rest.exception.DAOException;
import br.com.hackathon.rest.exception.NegocioException;
import br.com.hackathon.rest.model.Login;
import br.com.hackathon.rest.validador.Validador;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

/**
 *
 * @author Alexandre Feitosa Faustino <afeitosa29@gmail.com>
 * @date 18/02/2017
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ContaService {

    @Inject
    private LoginDAO loginDAO;
    
    @Inject
    private Validador validador;
    
    public Boolean logar(Login login) throws NegocioException{
        try {
            return loginDAO.consultarContaPorTelefoneSenha(login.getTelefone(), login.getSenha()) != null;
        } catch (DAOException e) {
            throw new NegocioException(e);
        }
    }
    
}
