/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.services;

import br.com.hackathon.rest.model.Conta;
import br.com.hackathon.rest.model.Evento;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alexandre Feitosa Faustino <afeitosa29@gmail.com>
 */
@Path("/rest/evento")
public class EventoRest {
    
    @POST
    @Path("/cadastraEvento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastraConta(Evento evento){
        
        return Response.ok().entity("Servidor disponivel").build();
    }
    
}
