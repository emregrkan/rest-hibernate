package net.sni.resthibernate.service.impl;

import net.sni.resthibernate.dao.TeamDao;
import net.sni.resthibernate.entity.Team;
import net.sni.resthibernate.service.TeamService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.Set;

@Singleton
public class TeamServiceImpl implements TeamService {

    @Inject
    private TeamDao teamDao;

    @Override
    public Optional<Team> findOneById(final Long id) {
        return teamDao.findOneById(id);
    }

    @Override
    public Set<Team> findAll() {
        return teamDao.findAll();
    }

    @Override
    public void saveOrUpdate(Team entity) {
        teamDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Team entity) {
        teamDao.delete(entity);
    }

    @Override
    public void deleteById(final Long id) {
        teamDao.deleteById(id);
    }

    @Override
    public boolean addProjectToTeam(final Long teamId, final Long projectId) {
        return teamDao.addProjectToTeam(teamId, projectId);
    }
}
