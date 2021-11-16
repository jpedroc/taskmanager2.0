package br.gov.basis.taskmanager.taskservice.service.mapper;

import br.gov.basis.taskmanager.taskservice.domain.elastic.TaskDocument;
import br.gov.basis.taskmanager.taskservice.service.dto.listing.TaskListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskListMapper extends EntityMapper<TaskListDTO, TaskDocument> {
}
