package net.sni.resthibernate.util;

import net.sni.resthibernate.entity.Intern;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import net.sni.resthibernate.entity.Employee;
import net.sni.resthibernate.entity.Project;
import net.sni.resthibernate.entity.Team;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class SessionFactoryUtil {

    private static final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Team.class)
            .addAnnotatedClass(Employee.class)
            .addAnnotatedClass(Intern.class)
            .addAnnotatedClass(Project.class)
            .buildSessionFactory();

    @Produces
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @PreDestroy
    public void closeSessionFactory() {
        System.out.println("INFO: SessionFactoryUtil: closeSessionFactory() called.");

        if (sessionFactory != null && sessionFactory.isOpen()) {
            final Session currentSession = sessionFactory.getCurrentSession();

            if (currentSession != null && currentSession.isOpen()) {
                currentSession.close();
            }

            sessionFactory.close();
            System.out.println("INFO: SessionFactoryUtil: closeSessionFactory() succeed.");
        }
    }
}
