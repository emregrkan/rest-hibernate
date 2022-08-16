package net.sni.resthibernate.service.impl;

import net.sni.resthibernate.dao.EmployeeDao;
import net.sni.resthibernate.entity.Employee;
import net.sni.resthibernate.service.EmployeeService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.Set;

@Singleton
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeDao employeeDao;

    @Override
    public Optional<Employee> findOneById(final Long id) {
        return employeeDao.findOneById(id);
    }

    @Override
    public Set<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public void saveOrUpdate(Employee entity) {
        employeeDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Employee entity) {
        employeeDao.delete(entity);
    }

    @Override
    public void deleteById(final Long id) {
        employeeDao.deleteById(id);
    }

    @Override
    public boolean addProjectToEmployee(final Long employeeId, final Long projectId) {
        return employeeDao.addProjectToEmployee(employeeId, projectId);
    }
}
