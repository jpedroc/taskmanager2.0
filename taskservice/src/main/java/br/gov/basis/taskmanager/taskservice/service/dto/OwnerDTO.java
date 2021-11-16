package br.gov.basis.taskmanager.taskservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class OwnerDTO implements Serializable {

    private Integer id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Integer statusId;
}
