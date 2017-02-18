package br.com.hackathon.rest.interfaces;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @param <T>
 * @param <K>
 * 
 */
public interface Persistencia <T, K>{
   
    /**
     * Gravar persiste um novo elemento no banco de dados, caso sucesso é retornado um elemento com ID preechida
     * @param t
     * @return T
     * @throws TransactionRequiredException
     * @throws PersistenceException
     * @throws IllegalArgumentException 
     */
    public T gravar(T t) throws PersistenceException, IllegalArgumentException;
    
    /**
     * Atualizar um elemento do banco de dados, caso sucesso é retornado o elemeto
     * @param t
     * @return T
     * @throws TransactionRequiredException
     * @throws PersistenceException
     * @throws IllegalArgumentException 
     */
    public T atualizar(T t) throws PersistenceException, IllegalArgumentException;
    
    /**
     * Buscar
     * @param k
     * @return T
     * @throws IllegalArgumentException 
     */
    public T buscar(K k) throws IllegalArgumentException;
    
    /**
     * Remover um elemento do banco de dado
     * @param t
     * @throws TransactionRequiredException
     * @throws IllegalArgumentException 
     */
    public void remover(T t) throws IllegalArgumentException;
    
    /**
     * Recupera a instância do EntityManager criado
     * 
     * @return EntityManager
     */
    public EntityManager getEntityManager();
    
    /**
     * Recupera uma lista de elemento. Recebe um Map<?, ?> de parametros e uma String representando um Named Query
     * 
     * @param namedQuery
     * @param parametros
     * @return List
     */
    public List<T> consultar(String namedQuery, Map<?, ?> parametros);
    
}
