package net.sni.resthibernate.dao;

import net.sni.resthibernate.entity.Project;

public class ProjectDao extends GenericDao<Project, Long> {
    public ProjectDao() {
        super.setClazz(Project.class);
    }
}
