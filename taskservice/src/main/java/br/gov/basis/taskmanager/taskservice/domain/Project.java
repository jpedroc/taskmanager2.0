package br.gov.basis.taskmanager.taskservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_project")
    @SequenceGenerator(name = "seq_project", sequenceName = "seq_project", allocationSize = 1)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "url_repository", nullable = false)
    private String urlRepository;

    @Column(name = "code_number", nullable = false)
    private String codeNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusProject statusProject;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "conclusion_date")
    private LocalDate conclusionDate;

}
