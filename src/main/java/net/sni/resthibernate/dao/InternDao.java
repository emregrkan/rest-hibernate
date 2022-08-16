package net.sni.resthibernate.dao;

import net.sni.resthibernate.entity.Intern;

public class InternDao extends GenericDao<Intern, Long> {
    public InternDao() {
        super.setClazz(Intern.class);
    }
}
