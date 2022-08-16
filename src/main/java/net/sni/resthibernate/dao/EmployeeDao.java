package net.sni.resthibernate.dao;

import org.hibernate.Session;
import net.sni.resthibernate.entity.Employee;
import net.sni.resthibernate.entity.Project;
import net.sni.resthibernate.entity.Team;

import javax.inject.Singleton;

@Singleton
public class EmployeeDao extends GenericDao<Employee, Long> {
    private EmployeeDao() {
        super.setClazz(Employee.class);
    }

    public boolean addProjectToEmployee(final Long employeeId, final Long projectId) {
        boolean isAdded = false;
        final Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        final Employee foundEmployee = session.get(Employee.class, employeeId);
        final Project foundProject = session.get(Project.class, projectId);

        if (foundEmployee != null) {
            final Team team = foundEmployee.getTeam();

            if (team != null) {
                if (foundProject != null) {
                    team.getProjects().add(foundProject);
                    foundEmployee.getProjects().add(foundProject);
                    isAdded = true;
                }
            }
        }
        session.getTransaction().commit();

        return isAdded;
    }
}
