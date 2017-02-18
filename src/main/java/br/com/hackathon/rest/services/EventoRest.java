/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.services;

import br.com.hackathon.rest.business.EventoService;
import br.com.hackathon.rest.exception.NegocioException;
import br.com.hackathon.rest.model.Evento;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alexandre Feitosa Faustino <afeitosa29@gmail.com>
 */
@Path("/rest/eventos")
public class EventoRest {
    
    @Inject
    private EventoService eventoService;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastraEvento(Evento evento){
        try {
            return Response.ok().entity( eventoService.cadastrarEvento(evento) ).build();
        } catch (NegocioException ex) {
            throw new WebApplicationException(ex);
        }
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response confirmarEvento(Evento evento){
        try {
            return Response.ok().entity( eventoService.confirmarParticipacao(evento) ).build();
        } catch (NegocioException ex) {
            throw new WebApplicationException(ex);
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarEvenot(@PathParam("id")Long idEvento){
        try {
            return Response.ok().entity( eventoService.excluirEvento(idEvento) ).build();
        } catch (NegocioException ex) {
            throw new WebApplicationException(ex);
        }
    }
    
    @GET
    @Path("/ocorridos/participante/{telefone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarEventoOcorridos(@PathParam("telefone")String telefone){
        try {
            return Response.ok().entity( eventoService.consultarEventosOcorridosPorParticipantes(telefone) ).build();
        } catch (NegocioException ex) {
            throw new WebApplicationException(ex);
        }
    }
    
    @GET
    @Path("/atuais/participante/{telefone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response conusltarEventosAtuais(@PathParam("telefone")String telefone){
        try {
            return Response.ok().entity( eventoService.consultarEventosAtuaisPorParticipantes(telefone) ).build();
        } catch (NegocioException ex) {
            throw new WebApplicationException(ex);
        }
    }
}
