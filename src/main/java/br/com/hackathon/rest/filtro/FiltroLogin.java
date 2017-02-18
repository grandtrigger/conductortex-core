/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
@WebFilter(urlPatterns = "/api/login", filterName = "login")
public class FiltroLogin implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //FILTRO DE LOGIN
        System.out.println("Filtro addp");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //
    }

}
