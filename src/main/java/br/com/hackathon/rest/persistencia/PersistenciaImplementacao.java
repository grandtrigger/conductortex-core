package br.com.hackathon.rest.persistencia;

import br.com.hackathon.rest.interfaces.Persistencia;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @param <T>
 * @param <K>
 * @date 21/01/2017
 *
 */
@Dependent
public class PersistenciaImplementacao <T, K> implements Persistencia<T, K> {

    private final EntityManager entityManager;
    private final Class<T> klass;
    
    public PersistenciaImplementacao(Class<T> klass, EntityManager entityManager) {
        this.klass = klass;
        this.entityManager = entityManager;
    }
    
    @Override
    public T gravar(T t) throws PersistenceException, IllegalArgumentException {
        try {
            T entity = entityManager.merge(t);
            entityManager.flush();
            return entity;
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae);
        } catch (TransactionRequiredException tre){
            throw new PersistenceException(tre);
        } catch(PersistenceException pe){
            throw new PersistenceException(pe);
        }
    }

    @Override
    public T atualizar(T t) throws PersistenceException, IllegalArgumentException {
        try {
            T entity = entityManager.merge(t);
            entityManager.flush();
            return entity;
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae);
        } catch (TransactionRequiredException tre){
            throw new PersistenceException(tre);
        } catch(PersistenceException pe){
            throw new PersistenceException(pe);
        }
    }

    @Override
    public T buscar(K k) throws IllegalArgumentException {
        try {
            return entityManager.find(klass, k);
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae);
        }
    }

    @Override
    public void remover(T t) throws IllegalArgumentException {
        try {
            entityManager.remove(t);
        }  catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae);
        } catch (TransactionRequiredException tre){
            throw new PersistenceException(tre);
        } 
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
