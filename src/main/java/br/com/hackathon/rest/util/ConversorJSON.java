package br.com.hackathon.rest.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Conversor de arquivos JSON, esta classe tem a finalidade de prover métodos
 * resposáveis por realizar o parse de Objetos para o padrão de arquivo JSON,
 * bem como realizar o processo inverso, transformar um arquivo JSON em um
 * objeto.
 *
 * @author Felipe de Brito Lira
 * @param <T>
 */
public class ConversorJSON<T> implements Serializable {

    private final Gson gson;
    private final Class classe;

    private ConversorJSON(Class classe) {
        this.gson = new Gson();
        this.classe = classe;
    }

    @Produces
    private ConversorJSON<T> produces(InjectionPoint injectionPoint){
        ParameterizedType type = (ParameterizedType) injectionPoint.getType();
        Class classeInjecao = (Class) type.getActualTypeArguments()[0];
        return new ConversorJSON<>(classeInjecao);
    }
    
    /**
     * Este método realiza o parse do Objeto para o formato JSON.
     *
     * @param t
     * @return String - representação do objeto
     */
    public String converteJSON(T t) {
        return this.gson.toJson(t);
    }

    /**
     * Este método realiza o parse de uma String json para um objeto
     * especificado.
     *
     * @param json - string de representação do json
     * @return Object - objeto deserializado
     * @throws JsonSyntaxException - exceção lançanda caso a representação do
     * json não esteja no padrão RFC 4627.
     */
    public T converteObject(String json) throws JsonSyntaxException {
        try {
            return (T)this.gson.fromJson(json, this.classe);
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException(String.format("Arquivo JSON fora do padrão RFC 4627.\nMensagem: %s", e.getMessage()));
        }

    }

}
