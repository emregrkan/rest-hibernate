package net.sni.resthibernate.service;

import java.util.Optional;
import java.util.Set;

public interface GenericService<T, ID> {
    Optional<T> findOneById(final ID id);

    Set<T> findAll();

    void saveOrUpdate(T entity);

    void delete(T entity);

    void deleteById(final ID id);
}

