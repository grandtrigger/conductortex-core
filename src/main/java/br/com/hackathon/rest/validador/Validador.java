/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.validador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 */
public class Validador implements Serializable{

    private List<String> erros;
    
    public Validador(){
        this.erros = new ArrayList<>();
    }
    
    /**
     * Este método é responsavel por validar um objeto. A validação é feito nos
     * atributos dos objetos. Retorna true caso os campos definidos no objeto
     * sejam válidos, caso contrário retorna false se ao menos um campos for
     * inválido.
     *
     * @param obj
     * @return boolean - true se o objeto for válido, falso caso contrário
     */
    public Boolean validar(Object obj) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator v = factory.getValidator();
        for(ConstraintViolation<Object> constraint : v.validate(obj) ){
            erros.add( constraint.getMessage() );
        }
        return v.validate(obj).isEmpty();
    }
    
    /**
     * Retorna a lista de erros de um objeto apos o processo de validação
     * @return String
     */
    public String getErro(){
        return erros.toString();
    }
}
