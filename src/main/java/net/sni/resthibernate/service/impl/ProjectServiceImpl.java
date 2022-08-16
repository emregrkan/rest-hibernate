package net.sni.resthibernate.service.impl;

import net.sni.resthibernate.service.ProjectService;
import net.sni.resthibernate.dao.ProjectDao;
import net.sni.resthibernate.entity.Project;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.Set;

@Singleton
public class ProjectServiceImpl implements ProjectService {

    @Inject
    private ProjectDao projectDao;

    @Override
    public Optional<Project> findOneById(final Long id) {
        return projectDao.findOneById(id);
    }

    @Override
    public Set<Project> findAll() {
        return projectDao.findAll();
    }

    @Override
    public void saveOrUpdate(Project entity) {
        projectDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Project entity) {
        projectDao.delete(entity);
    }

    @Override
    public void deleteById(final Long id) {
        projectDao.deleteById(id);
    }
}
