package net.sni.resthibernate.service.impl;

import net.sni.resthibernate.entity.Intern;
import net.sni.resthibernate.dao.InternDao;
import net.sni.resthibernate.service.InternService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.Set;

@Singleton
public class InternServiceImpl implements InternService {

    @Inject
    private InternDao internDao;

    @Override
    public Optional<Intern> findOneById(final Long id) {
        return internDao.findOneById(id);
    }

    @Override
    public Set<Intern> findAll() {
        return internDao.findAll();
    }

    @Override
    public void saveOrUpdate(Intern entity) {
        internDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Intern entity) {
        internDao.delete(entity);
    }

    @Override
    public void deleteById(final Long id) {
        internDao.deleteById(id);
    }
}
