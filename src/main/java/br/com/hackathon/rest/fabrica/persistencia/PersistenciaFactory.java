package br.com.hackathon.rest.fabrica.persistencia;

import br.com.hackathon.rest.interfaces.Persistencia;
import br.com.hackathon.rest.persistencia.PersistenciaImplementacao;
import java.lang.reflect.ParameterizedType;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @param <T>
 * @param <K>
 * @date 21/01/2017
 *
 */
public class PersistenciaFactory<T, K> {

    @PersistenceContext(unitName = "hackathon")
    private EntityManager entityManager;

    /**
     * Fabrica um novo EntityManager
     *
     * @param injectionPoint
     * @return Persistencia<E, K>
     */
    @Produces
    private Persistencia<T, K> create(InjectionPoint injectionPoint) {
        ParameterizedType type = (ParameterizedType) injectionPoint.getType();
        Class classe = (Class) type.getActualTypeArguments()[0];
        return new PersistenciaImplementacao(classe, entityManager);
    }

}
