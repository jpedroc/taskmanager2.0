package br.gov.basis.taskmanager.taskservice.service.dto.listing;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OwnerListDTO implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String birthDate;
    private String status;
}
