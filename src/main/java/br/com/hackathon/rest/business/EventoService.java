/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.business;

import br.com.hackathon.rest.dao.EventoDAO;
import br.com.hackathon.rest.exception.DAOException;
import br.com.hackathon.rest.exception.NegocioException;
import br.com.hackathon.rest.model.Evento;
import java.util.ArrayList;
import java.util.List;
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
public class EventoService {

    @Inject
    private EventoDAO eventoDAO;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean cadastrarEvento(Evento evento)throws NegocioException{
        try {
            return eventoDAO.cadastraEvento(evento) != null;
        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean atualizarEvento(Evento evento)throws NegocioException{
        try {
            return eventoDAO.atualizarEvento(evento) != null;
        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean confirmarParticipacao(Evento evento)throws NegocioException{
        try {
            return eventoDAO.atualizarEvento(evento) != null;
        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean excluirEvento(Long idEvento)throws NegocioException{
        try {
            Evento evento = eventoDAO.consultarEvento(idEvento);
            eventoDAO.excluirEvento(evento);
            return Boolean.TRUE;
        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }
    
    public List<Evento> consultarTodosEventosAtuais() throws NegocioException{
        try {
            return eventoDAO.consultarTodosEventosAtuais();
        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }
    
    public List<Evento> consultarEventosOcorridosPorParticipantes(String telefone)throws NegocioException{
        try {
            return eventoDAO.consultarEventosOcorridosPorParticipante(telefone);
        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }
    
    public List<Evento> consultarEventosAtuaisPorParticipantes(String telefone)throws NegocioException{
        try {
            List<Evento> eventos = new ArrayList<>();
            eventos.addAll( eventoDAO.consultarEventosPendentesPorParticipante(telefone) );
            eventos.addAll( eventoDAO.consultarEventosConfirmadosPorParticipante(telefone) );
            return eventos;
        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }
    
}
