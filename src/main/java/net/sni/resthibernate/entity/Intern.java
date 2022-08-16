package net.sni.resthibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "intern")
public class Intern {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne
    @MapsId
    @JoinColumn(name = "buddy_id", nullable = false)
    @JsonIgnoreProperties(value = {"intern", "projects"}, allowSetters = true)
    private Employee buddy;
}