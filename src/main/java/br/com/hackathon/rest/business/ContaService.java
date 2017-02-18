/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.business;

import br.com.hackathon.rest.criptografia.Encriptor;
import br.com.hackathon.rest.dao.ContaDAO;
import br.com.hackathon.rest.enumeracoes.MensagensCodigo;
import br.com.hackathon.rest.exception.DAOException;
import br.com.hackathon.rest.exception.NegocioException;
import br.com.hackathon.rest.model.Conta;
import br.com.hackathon.rest.util.MensagensBase;
import br.com.hackathon.rest.validador.StringValidador;
import br.com.hackathon.rest.validador.Validador;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
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
    private ContaDAO contaDAO;
    
    @Inject
    private Validador validador;
    
    @Inject
    private StringValidador stringValidador;
    
    @Inject
    private MensagensBase mensagensBase;
    
    @Inject
    private Encriptor encriptor;
    
    /**
     * Metodo que cadastra uma nova Conta. Retorna true se a conta for persistida ou retorna false caso não seja persistida
     * @param conta
     * @return Boolean
     * @throws NegocioException 
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean cadastrar(Conta conta) throws NegocioException{
        try {
            if( !validador.validar(conta) ){
                throw new NegocioException( validador.getErro() );
            }
            conta.setSenha( encriptor.encrypt(conta.getSenha() ) );
            return contaDAO.cadastraConta(conta) != null;
        } catch (DAOException | NoSuchAlgorithmException| NoSuchPaddingException ex) {
            throw new NegocioException(ex);
        }
    }
    
    /**
     * Retorna uma Conta informando o Telefone para consultar
     * @param telefone
     * @return Conta
     * @throws NegocioException 
     */
    public Conta consultarContaPorTelefone(String telefone) throws NegocioException{
        try {
            if( !stringValidador.isValid(telefone) ){
                throw new NegocioException( mensagensBase.get(MensagensCodigo.MS006));
            }
            return contaDAO.consultaContaPorTelefone(telefone);
        } catch (DAOException e) {
            throw new NegocioException(e);
        }
    }
    
    /**
     * Retorna uma Conta utilizando o CPF para consultar
     * @param cpf
     * @return Conta
     * @throws NegocioException 
     */
    public Conta consultarContaPorCpf(String cpf) throws NegocioException{
        try {
            if( !stringValidador.isValid(cpf) ){
                throw new NegocioException( mensagensBase.get(MensagensCodigo.MS005));
            }
            return contaDAO.consultaContaPorCpf(cpf);
        } catch (DAOException e) {
            throw new NegocioException(e);
        }
    }
    
    /**
     * Retorna uma Conta utilizando o email para consultar
     * @param email
     * @return Conta
     * @throws NegocioException 
     */
    public Conta consultarContaPorEmail(String email) throws NegocioException{
        try {
            if( !stringValidador.isValid(email) ){
                throw new NegocioException( mensagensBase.get(MensagensCodigo.MS004));
            }
            return contaDAO.consultaContaPorEmail(email);
        } catch (DAOException e) {
            throw new NegocioException(e);
        }
    }
    
}
