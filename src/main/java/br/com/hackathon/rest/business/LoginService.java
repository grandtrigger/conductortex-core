/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.business;

import br.com.hackathon.rest.dao.LoginDAO;
import br.com.hackathon.rest.exception.DAOException;
import br.com.hackathon.rest.exception.NegocioException;
import br.com.hackathon.rest.model.Conta;
import br.com.hackathon.rest.model.Login;
import br.com.hackathon.rest.token.GerenciadorToken;
import br.com.hackathon.rest.token.Token;
import br.com.hackathon.rest.validador.ObjetoValidador;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
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
    
    @Inject
    private ObjetoValidador objetoValidador;
    
    @Inject
    private GerenciadorToken gerenciadorToken;
    
    public Token logar(Login login) throws NegocioException{
        try {
            Conta conta  = loginDAO.consultarContaPorTelefoneSenha(login.getTelefone(), login.getSenha());
            if( objetoValidador.isValid( conta ) ){
                return new Token( gerenciadorToken.gerador( "felipedebritolira@gmial.com", 30L) );
            }
            return null;
        } catch (DAOException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
            throw new NegocioException(ex);
        }
    }
    
}
