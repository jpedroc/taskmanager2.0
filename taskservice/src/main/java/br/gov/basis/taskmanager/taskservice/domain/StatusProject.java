package br.gov.basis.taskmanager.taskservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "status_project")
@Entity
@Getter
@Setter
public class StatusProject implements Serializable {

    @Id
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

}
