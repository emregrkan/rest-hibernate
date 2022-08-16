package net.sni.resthibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Boolean isActive;
    private String name;
    private String description;
    private LocalDateTime assignmentDate;
    private LocalDateTime dueDate;
    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"intern", "projects"}, allowSetters = true)
    private Set<Employee> employees = new HashSet<>();
    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"employees", "projects"}, allowSetters = true)
    private Set<Team> teams = new HashSet<>();
}
