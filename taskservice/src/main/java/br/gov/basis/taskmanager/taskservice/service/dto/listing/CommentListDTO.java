package br.gov.basis.taskmanager.taskservice.service.dto.listing;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CommentListDTO implements Serializable {
    private Integer id;
    private String description;
    private String task;
    private String owner;
}
