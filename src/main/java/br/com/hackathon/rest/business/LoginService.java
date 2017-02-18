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
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class LoginService {

    @Inject
    private LoginDAO loginDAO;
    
    public Boolean logar(Login login) throws NegocioException{
        try {
            return loginDAO.consultarContaPorTelefoneSenha(login.getTelefone(), login.getSenha()) != null;
        } catch (DAOException e) {
            throw new NegocioException(e);
        }
    }
    
}
