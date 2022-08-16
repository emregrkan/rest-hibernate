package net.sni.resthibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class GenericDao<T, ID extends Serializable> {

    @Inject
    protected SessionFactory sessionFactory;
    private Class<T> clazz;

    public final void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Optional<T> findOneById(final ID id) {
        final Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        final T foundEntity = session.get(clazz, id);
        session.getTransaction().commit();

        return Optional.ofNullable(foundEntity);
    }

    public Set<T> findAll() {
        final Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        final List<T> resultList = session.createQuery("from ".concat(clazz.getName()), clazz).getResultList();
        session.getTransaction().commit();

        return new HashSet<>(resultList);
    }

    public void saveOrUpdate(T entity) {
        final Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
    }

    public void delete(T entity) {
        final Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.remove(entity);
        session.getTransaction().commit();
    }

    public void deleteById(final ID id) {
        final Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        final T entityToBeRemoved = session.find(clazz, id);
        session.remove(entityToBeRemoved);
        session.getTransaction().commit();
    }
}
