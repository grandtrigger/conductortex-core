/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.business;

import br.com.hackathon.rest.dao.EventoDAO;
import br.com.hackathon.rest.dao.NotificacaoDAO;
import br.com.hackathon.rest.exception.DAOException;
import br.com.hackathon.rest.exception.NegocioException;
import br.com.hackathon.rest.model.Evento;
import br.com.hackathon.rest.model.Participante;
import br.com.hackathon.rest.validador.ObjetoValidador;
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

    @Inject
    private ObjetoValidador objetoValidador;

    @Inject
    private NotificacaoDAO notificacaoDAO;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean cadastrarEvento(Evento evento) throws NegocioException {
        try {
            Evento novoEvento = eventoDAO.cadastraEvento(evento);
            if (objetoValidador.isValid(novoEvento)) {
                notificacaoDAO.sendNotificacaoConfirmacao(evento);
            } else {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean atualizarEvento(Evento evento) throws NegocioException {
        try {
            return eventoDAO.atualizarEvento(evento) != null;
        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean confirmarParticipacao(Evento evento) throws NegocioException {
        try {
            
            Evento eventoAtualizado = eventoDAO.atualizarEvento(evento);
            
            if (objetoValidador.isValid(eventoAtualizado)) {
                
                Boolean confirmacao = true;
                
                for (Participante participante : eventoAtualizado.getParticipantes()) {
                    if (!participante.getConfirmacao()) {
                        confirmacao = false;
                    }
                }

                if (confirmacao) {
                    notificacaoDAO.sendNotificacaoEventoConfirmado(evento);
                }
                
            } else {
                return Boolean.FALSE;
            }

            return Boolean.TRUE;

        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean excluirEvento(Long idEvento) throws NegocioException {
        try {
            Evento evento = eventoDAO.consultarEvento(idEvento);
            eventoDAO.excluirEvento(evento);
            notificacaoDAO.sendNotificacaoEventoCancelado(evento);
            return Boolean.TRUE;
        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }

    public List<Evento> consultarTodosEventosAtuais() throws NegocioException {
        try {
            return eventoDAO.consultarTodosEventosAtuais();
        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }

    public List<Evento> consultarEventosOcorridosPorParticipantes(String telefone) throws NegocioException {
        try {
            List<Evento> eventos = new ArrayList<>();
            eventos.addAll( eventoDAO.consultarEventosOcorridosPorParticipante(telefone) );
            eventos.addAll( eventoDAO.consultarEventosOcorridosPorCriador(telefone) );
            return eventos;
        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }

    public List<Evento> consultarEventosAtuaisPorParticipantes(String telefone) throws NegocioException {
        try {
            List<Evento> eventos = new ArrayList<>();
            eventos.addAll( eventoDAO.consultarEventosPendentesPorParticipante(telefone) );
            eventos.addAll( eventoDAO.consultarEventosPendentesPorCriador(telefone) );
            eventos.addAll( eventoDAO.consultarEventosConfirmadosPorParticipante(telefone) );
            eventos.addAll( eventoDAO.consultarEventosConfirmadosPorCriador(telefone) );
            return eventos;
        } catch (DAOException ex) {
            throw new NegocioException(ex);
        }
    }

}
