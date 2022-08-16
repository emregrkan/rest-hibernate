package net.sni.resthibernate.service;

import net.sni.resthibernate.entity.Team;

public interface TeamService extends GenericService<Team, Long> {
    boolean addProjectToTeam(final Long teamId, final Long projectId);
}
