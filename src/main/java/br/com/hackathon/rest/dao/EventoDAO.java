/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.dao;

import br.com.hackathon.rest.enumeracoes.MensagensCodigo;
import br.com.hackathon.rest.enumeracoes.TipoEvento;
import br.com.hackathon.rest.exception.DAOException;
import br.com.hackathon.rest.exception.PersistenciaException;
import br.com.hackathon.rest.interfaces.Persistencia;
import br.com.hackathon.rest.model.Evento;
import br.com.hackathon.rest.model.Participante;
import br.com.hackathon.rest.util.MensagensBase;
import java.util.ArrayList;
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
public class EventoDAO {

    @Inject
    private Persistencia<Evento, Long> dao;

    @Inject
    private MensagensBase mensagensBase;

    public List<Evento> consultarTodosEventosAtuais() throws DAOException {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("evento", TipoEvento.CONFIRMADO);
            return dao.consultar("vento.consultar.todos.eventos.atuais", parametros);
        } catch (PersistenciaException ex) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), ex);
        }
    }

    /**
     *
     * @param evento
     * @return
     * @throws DAOException
     */
    public Evento consultarEvento(Long evento) throws DAOException {
        try {
            return dao.buscar(evento);
        } catch (Exception e) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), e);
        }
    }

    /**
     *
     * @param evento
     * @throws DAOException
     */
    public void excluirEvento(Evento evento) throws DAOException {
        try {
            dao.remover(evento);
        } catch (Exception e) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), e);
        }
    }

    /**
     * Metodo que realiza o cadastro de um evento
     *
     * @param evento
     * @return Evento
     * @throws DAOException
     */
    public Evento cadastraEvento(Evento evento) throws DAOException {
        try {
            return dao.gravar(evento);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), ex);
        }//catch
    }//cadastraEvento

    /**
     *
     * @param evento
     * @return
     * @throws DAOException
     */
    public Evento atualizarEvento(Evento evento) throws DAOException {
        try {
            return dao.atualizar(evento);
        } catch (PersistenceException | IllegalArgumentException e) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), e);
        }
    }

    /**
     * Consultar eventos ocorridos por participanntes
     *
     * @param telefone
     * @return List
     * @throws br.com.hackathon.rest.exception.DAOException
     */
    public List<Evento> consultarEventosOcorridosPorParticipante(String telefone) throws DAOException {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("evento", TipoEvento.OCORRIDO);
            List<Evento> eventos = dao.consultar("evento.consultar.todos.eventos.atuais", parametros);
            return filtrar(eventos, telefone);
        } catch (PersistenciaException ex) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), ex);
        }
    }

    /**
     * Consultar eventos pendentes por participanntes
     *
     * @param telefone
     * @return List
     * @throws br.com.hackathon.rest.exception.DAOException
     */
    public List<Evento> consultarEventosPendentesPorParticipante(String telefone) throws DAOException {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("evento", TipoEvento.PENDENTE);
            List<Evento> eventos = dao.consultar("evento.consultar.todos.eventos.atuais", parametros);
            return filtrar(eventos, telefone);
        } catch (PersistenciaException ex) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), ex);
        }
    }

    /**
     * Consultar eventos confirmados por participanntes
     *
     * @param telefone
     * @return List
     * @throws br.com.hackathon.rest.exception.DAOException
     */
    public List<Evento> consultarEventosConfirmadosPorParticipante(String telefone) throws DAOException {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("evento", TipoEvento.CONFIRMADO);
            List<Evento> eventos = dao.consultar("evento.consultar.todos.eventos.atuais", parametros);
            return filtrar(eventos, telefone);
        } catch (PersistenciaException ex) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), ex);
        }
    }

    private List<Evento> filtrar(List<Evento> eventos, String telefone) {
        List<Evento> ocorridos = new ArrayList<>();
        for (Evento evento : eventos) {
            if (evento.getCriador().getConta().getTelefone().equals(telefone)) {
                ocorridos.add(evento);
            } else {
                for (Participante participante : evento.getParticipantes()) {
                    if (participante.getConta().getTelefone().equals(telefone)) {
                        ocorridos.add(evento);
                    }
                }
            }
        }
        return ocorridos;
    }

}//EventoDAO
