package net.sni.resthibernate.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import net.sni.resthibernate.entity.Project;
import net.sni.resthibernate.entity.Team;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class TeamDao extends GenericDao<Team, Long> {
    private TeamDao() {
        super.setClazz(Team.class);
    }
    @Override
    public Optional<Team> findOneById(final Long id) {
        final Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        final Team foundTeam = session.get(Team.class, id);

        if (foundTeam != null) {
            Hibernate.initialize(foundTeam.getEmployees());
            Hibernate.initialize(foundTeam.getProjects());
        }

        session.getTransaction().commit();

        return Optional.ofNullable(foundTeam);
    }

    public boolean addProjectToTeam(final Long teamId, final Long projectId) {
        boolean isAdded = false;
        final Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        final Team foundTeam = session.get(Team.class, teamId);
        final Project foundProject = session.get(Project.class, projectId);

        if (foundTeam != null) {
            if (foundProject != null) {
                foundTeam.getProjects().add(foundProject);
                isAdded = true;
            }
        }
        session.getTransaction().commit();

        return isAdded;
    }
}
