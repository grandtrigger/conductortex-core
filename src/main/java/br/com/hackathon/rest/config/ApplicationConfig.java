package br.com.hackathon.rest.config;

import br.com.hackathon.rest.services.ContaRest;
import br.com.hackathon.rest.services.EventoRest;
import br.com.hackathon.rest.services.LoginRest;
import br.com.hackathon.rest.services.VerificacaoDB;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 16/02/2017
 *
 */
@ApplicationPath("/api")
public class ApplicationConfig extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addClass(resources);
        return resources;
    }

    private void addClass(Set<Class<?>> resources){
        resources.add(VerificacaoDB.class);
        resources.add(LoginRest.class);
        resources.add(ContaRest.class);
        resources.add(EventoRest.class);
    }
    
}
