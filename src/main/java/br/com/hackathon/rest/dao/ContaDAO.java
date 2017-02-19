/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.dao;

import br.com.hackathon.rest.enumeracoes.MensagensCodigo;
import br.com.hackathon.rest.exception.DAOException;
import br.com.hackathon.rest.exception.PersistenciaException;
import br.com.hackathon.rest.interfaces.Persistencia;
import br.com.hackathon.rest.model.Conta;
import br.com.hackathon.rest.util.MensagensBase;
import br.com.hackathon.rest.validador.ListaValidador;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.persistence.PersistenceException;


/**
 *
 * @author Alexandre Feitosa Faustino <afeitosa29@gmail.com>
 * @date 18/02/2017
 *
 */
public class ContaDAO {
  
    @Inject
    private Persistencia<Conta, Long> dao;
      
    @Inject
    private ListaValidador listaValidador;
    
    @Inject
    private MensagensBase mensagensBase;
    
    /**
     * Método que cadastra uma nova conta
     * 
     * @param conta
     * @return Conta
     * @throws DAOException 
     */
    public Conta cadastraConta(Conta conta) throws DAOException{
        try {
            return dao.gravar(conta);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS003), ex);
        }
    }
    
    /**
     * Métoda que consulta uma conta por telefone
     * 
     * @param telefone
     * @return Conta
     * @throws DAOException 
     */
    public Conta consultaContaPorTelefone(String telefone) throws DAOException{
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("telefone", telefone);
            List<Conta> contas = dao.consultar("conta.consultar.conta.por.telefone", parametros);
            return listaValidador.isValid(contas) ? contas.get(0) : null;
        } catch (PersistenciaException ex) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), ex);
        }//catch
    }//consultaContaPorTelefone
    
    /**
     * Método que consulta um conta por cpf
     * 
     * @param cpf
     * @return Conta
     * @throws DAOException 
     */
    public Conta consultaContaPorCpf(String cpf) throws DAOException{
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("cpf", cpf);
            List<Conta> contas = dao.consultar("conta.consultar.conta.por.cpf", parametros);
            return listaValidador.isValid(contas) ? contas.get(0) : null;
        } catch (PersistenciaException ex) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), ex);
        }//catch
    }//consultaContaPorCpf
    
    /**
     * Método que consulta uma conta por email
     * 
     * @param email
     * @return Conta
     * @throws DAOException 
     */
    public Conta consultaContaPorEmail(String email) throws DAOException{
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("email", email);
            List<Conta> contas = dao.consultar("conta.consultar.conta.por.email", parametros);
            return listaValidador.isValid(contas) ? contas.get(0) : null;
        } catch (PersistenciaException ex) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), ex);
        }//catch
    }//consultaContaPorEmail
    
    /**
     * Método que recupera contas que existam na lista informada como parametro
     * 
     * @param lista
     * @return List
     * @throws DAOException 
     */
    public List<Conta> consultarContasAmigos(List<String> lista) throws DAOException{
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("telefones", lista);
            return dao.consultar("conta.consultar.contas.relacionadas.por.telefone", parametros);
        } catch (PersistenciaException ex) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), ex);
        }//catch
    }//consultaContaPorEmail

}//ContaDAO
