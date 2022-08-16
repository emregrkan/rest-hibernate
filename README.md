# SNI Internship - Week 6: REST API (JAX-RS Jersey) with Hibernate 

## Description
This REST application connects to a PostgreSQL database using Hibernate ORM.
<br>
<br>
There are four related entities in this application:
- **Team**:
    - Has _*one to many*_ relation with employee
    - Has _*many to many*_ relation with project
- **Employee**:
    - Has _*many to one*_ relation with team
    - Has _*one to one*_ relation with intern
    - Has _*many to many*_ relation with project
- **Intern**:
    - Has _*one to one*_ relation with employee
- **Project**:
    - Has _*many to many*_ relation with employee
    - Has _*many to many*_ relation with team

<br>

## Allowed HTTP request(s):
- **GET**: Get a resource or list of resources
- **POST**: Create a resource
- **PUT**: Update a resource or add a resource to related resource
- **DELETE**: Delete a resource

<br>

## References:
- ### Team:
  > Team is an entity that has many employees and projects
    - **Endpoints**:
        - <mark>/team/</mark> -- get all teams, create or delete the team
        - <mark>/team/:id</mark> -- find a specific team, update it or delete it
        - <mark>/team/:teamId/project/:projectId</mark> -- adds a project with given id to the team
    - **Attributes**:
        - <mark>id</mark> long -- The id of this team.
        - <mark>teamName</mark> string -- The name of this team.
        - <mark>employees</mark> set -- Employee set of this team.
        - <mark>project</mark> set of projects -- The set of assigned projects to this team.

<br>

- ### Employee:
  > Employee is an entity that has a team, an intern and projects
    - **Endpoints**:
        - <mark>/employee/</mark> -- get all employees, create or delete the employee
        - <mark>/employee/:id</mark> -- find a specific employee, update it or delete it
        - <mark>/employee/:employeeId/project/:projectId</mark> -- adds a project with given id to the employee
    - **Attributes**:
        - <mark>id</mark> long -- The id of this employee.
        - <mark>firstName</mark> string -- The first name of this employee.
        - <mark>lastName</mark> string -- The last name of this employee.
        - <mark>intern</mark> intern -- The intern assigned to this employee.
        - <mark>project</mark> set of projects -- The set of assigned projects to this employee.

<br>

- ### Intern:
  > Intern is an entity that has a buddy (employee).
    - **Endpoints**:
        - <mark>/intern/</mark> -- get all interns, create or delete the intern
        - <mark>/intern/:id</mark> -- find a specific intern, update it or delete it
    - **Attributes**:
        - <mark>id</mark> long -- The id of this intern.
        - <mark>firstName</mark> string -- The first name of this intern.
        - <mark>lastName</mark> string -- The last name of this intern.
        - <mark>buddy</mark> employee -- The employee assigned to this intern.

<br>

- ### Project:
  > Project is an entity resource is a large mass, planet or planetoid in the Star Wars Universe, at the time of 0 ABY.
    - **Endpoints**:
        - <mark>/project/</mark> -- get all project, create or delete the project
        - <mark>/project/:id</mark> -- find a specific project, update it or delete it
    - **Attributes**:
        - <mark>id</mark> long -- The id of this project.
        - <mark>isActive</mark> boolean -- The status of this project.
        - <mark>name</mark> string -- The name of this project.
        - <mark>description</mark> string -- The description of this project.
        - <mark>assignmentDate</mark> localdatetime -- The assignment date of this project.
        - <mark>dueDate</mark> localdatetime -- The deadline of this project.
        - <mark>employees</mark> set of employees -- The set of employees that this project assigned to.
        - <mark>teams</mark> set of teams -- The set of teams that this project assigned to.

<br>

## Packages:
| Package                          | Description                |
|----------------------------------|----------------------------|
| net.sni.resthibernate.config     | Provides config classes    |
| net.sni.resthibernate.controller | Provides REST controllers  |
| net.sni.resthibernate.entity     | Defines Hibernate Entities |
| net.sni.resthibernate.service    | Provides services          |
| net.sni.resthibernate.util       | Provides util classes      |
