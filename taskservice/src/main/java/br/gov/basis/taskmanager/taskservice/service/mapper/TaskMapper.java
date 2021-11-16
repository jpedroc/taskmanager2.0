package br.gov.basis.taskmanager.taskservice.service.mapper;

import br.gov.basis.taskmanager.taskservice.domain.Task;
import br.gov.basis.taskmanager.taskservice.service.dto.TaskDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CommentMapper.class})
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {
    @Override
    @Mapping(source = "ownerId", target = "owner.id")
    @Mapping(source = "statusId", target = "status.id")
    Task toEntity(TaskDTO taskDTO);

    @Override
    @InheritInverseConfiguration
    TaskDTO toDto(Task task);
}
