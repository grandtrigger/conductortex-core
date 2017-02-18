package br.com.hackathon.rest.persistencia;

import br.com.hackathon.rest.exception.PersistenciaException;
import br.com.hackathon.rest.interfaces.Persistencia;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
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
public class PersistenciaImplementacao<T, K> implements Persistencia<T, K> {

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
        } catch (TransactionRequiredException tre) {
            throw new PersistenceException(tre);
        } catch (PersistenceException pe) {
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
        } catch (TransactionRequiredException tre) {
            throw new PersistenceException(tre);
        } catch (PersistenceException pe) {
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
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae);
        } catch (TransactionRequiredException tre) {
            throw new PersistenceException(tre);
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<T> consultar(String namedQuery, Map<?, ?> parametros) throws PersistenciaException {
        try {
            Query query = this.entityManager.createNamedQuery(namedQuery);
            setParametros(query, parametros);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException(e);
        }
    }
    
    private void setParametros(Query query, Map<?, ?> parametros) {
        for (String key : (Set<String>) parametros.keySet()) {
            if (parametros.get(key) instanceof Date) {
                query.setParameter(key, (Date) parametros.get(key), TemporalType.DATE);
            } else {
                query.setParameter(key, parametros.get(key));
            }
        }
    }

}
