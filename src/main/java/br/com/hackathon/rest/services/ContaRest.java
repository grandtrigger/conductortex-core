/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.services;

import br.com.hackathon.rest.business.ContaService;
import br.com.hackathon.rest.exception.NegocioException;
import br.com.hackathon.rest.model.UsuarioAgendaCelular;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("/rest/contas")
public class ContaRest {

    @Inject
    private ContaService contaService;

    
    @GET
    @Path("telefone/{telefone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarContaPorTelefone(@PathParam("telefone")String telefone){
        try {
            return Response.ok().entity( contaService.consultarContaPorTelefone(telefone)).build();
        } catch (NegocioException ex) {
            throw new WebApplicationException(ex);
        }
    }
    
    @GET
    @Path("cpf/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarContaPorCpf(@PathParam("cpf")String cpf){
        try {
            return Response.ok().entity( contaService.consultarContaPorCpf(cpf)).build();
        } catch (NegocioException ex) {
            throw new WebApplicationException(ex);
        }
    }
    
    @GET
    @Path("email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarContaPorEmail(@PathParam("email")String email){
        try {
            return Response.ok().entity( contaService.consultarContaPorEmail(email)).build();
        } catch (NegocioException ex) {
            throw new WebApplicationException(ex);
        }
    }
    
    @POST
    @Path("/amigos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consultarContasPorTelefone(UsuarioAgendaCelular usuario){
        try {
            return Response.ok().entity( contaService.consultarContasAmigos(usuario.getTelefones())).build();
        } catch (NegocioException ex) {
            throw new WebApplicationException(ex);
        }
    }
    
}
