/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.services;

import br.com.hackathon.rest.business.LoginService;
import br.com.hackathon.rest.exception.NegocioException;
import br.com.hackathon.rest.model.Login;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
@Path("/login")
public class LoginRest {

    @Inject
    private LoginService loginService;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Login login){
        try {
            return Response.ok().entity( loginService.logar(login) ).build();
        } catch (NegocioException ex) {
            throw new WebApplicationException(ex);
        }
    }
    
}
