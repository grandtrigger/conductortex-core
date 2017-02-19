/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.interfaces;

import br.com.hackathon.rest.business.EventoService;
import br.com.hackathon.rest.enumeracoes.TipoEvento;
import br.com.hackathon.rest.exception.NegocioException;
import br.com.hackathon.rest.model.Evento;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
@Stateless
public class Agendador {

    @Inject
    private EventoService eventoService;
    
    @Schedule(hour = "0", minute = "0", dayOfMonth = "*", month = "*", year = "*", persistent = false)
    private void agendador(){
        try {
            LocalDate data = LocalDate.now();
            List<Evento> eventos = eventoService.consultarTodosEventosAtuais();
            for(Evento evento : eventos){
                if( data.isAfter( evento.getDataCriacao()) ){
                    evento.setTipoEvento(TipoEvento.OCORRIDO);
                    eventoService.atualizarEvento(evento);
                }
            }
        } catch (NegocioException ex) {
            Logger.getLogger(Agendador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
