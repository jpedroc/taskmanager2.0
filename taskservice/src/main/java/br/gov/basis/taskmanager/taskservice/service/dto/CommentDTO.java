package br.gov.basis.taskmanager.taskservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CommentDTO implements Serializable {
    private Integer id;
    private String description;
    private Integer taskId;
    private OwnerDTO owner;
}
