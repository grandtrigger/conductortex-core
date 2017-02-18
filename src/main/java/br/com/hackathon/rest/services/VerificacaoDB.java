/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
@Path("/rest/verificaoBD")
public class VerificacaoDB {

    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatusServidor(){
        return Response.ok().entity("Servidor disponivel").build();
    }
    
}
