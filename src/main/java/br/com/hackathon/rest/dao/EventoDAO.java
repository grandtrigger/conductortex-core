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
import br.com.hackathon.rest.model.Evento;
import br.com.hackathon.rest.util.MensagensBase;
import br.com.hackathon.rest.validador.ListaValidador;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Alexandre Feitosa Faustino <afeitosa29@gmail.com>
 * @date 18/02/2017
 *
 */
public class EventoDAO {

    @Inject
    private Persistencia<Evento, Long> dao;
      
    @Inject
    private ListaValidador listaValidador;
    
    @Inject
    private MensagensBase mensagensBase;
    
    public Evento cadastraEvento(Evento evento) throws DAOException{
        
        try {
            
            return dao.gravar(evento);
            
        } catch (Exception ex) {
            
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), ex);
            
        }//catch

    }//cadastraEvento
    
    public Evento consultaEventoPorTelefone(String telefone) throws DAOException{
        
        try {
            
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("telefone", telefone);
            //TODO
            List<Evento> eventos = dao.consultar("evento.consultar.evento.por.evento.criador.telefone", parametros);
            
            return listaValidador.isValid(eventos) ? eventos.get(0) : null;
            
        } catch (PersistenciaException ex) {
            
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), ex);
            
        }//catch

    }//consultaEventoPorTelefone
    
}//EventoDAO
