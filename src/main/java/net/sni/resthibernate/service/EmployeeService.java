package net.sni.resthibernate.service;

import net.sni.resthibernate.entity.Employee;

public interface EmployeeService extends GenericService<Employee, Long> {
    boolean addProjectToEmployee(final Long employeeId, final Long projectId);
}
