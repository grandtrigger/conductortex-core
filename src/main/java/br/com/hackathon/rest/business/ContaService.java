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
import br.com.hackathon.rest.validador.ListaValidador;
import br.com.hackathon.rest.validador.StringValidador;
import java.security.NoSuchAlgorithmException;
import java.util.List;
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
    private StringValidador stringValidador;
    
    @Inject
    private MensagensBase mensagensBase;
    
    @Inject
    private Encriptor encriptor;
    
    @Inject
    private ListaValidador listaValidador;
    
    /**
     * Metodo que cadastra uma nova Conta. Retorna true se a conta for persistida ou retorna false caso não seja persistida
     * @param conta
     * @return Boolean
     * @throws NegocioException 
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean cadastrar(Conta conta) throws NegocioException{
        try {
            conta.getSeguranca().setSenha( encriptor.encrypt(conta.getSeguranca().getSenha() ) );
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
    
    /**
     * Retorna uma lista de contas que possuem telefone dentro da lista de telefones
     * @param lista
     * @return List
     * @throws NegocioException 
     */
    public List<Conta> consultarContasAmigos(List<String> lista) throws NegocioException{
        try {
            if( !listaValidador.isValid(lista) ){
                throw new NegocioException( mensagensBase.get(MensagensCodigo.MS007));
            }
            return contaDAO.consultarContasAmigos(lista);
        } catch (DAOException e) {
            throw new NegocioException(e);
        }
    }
    
}
