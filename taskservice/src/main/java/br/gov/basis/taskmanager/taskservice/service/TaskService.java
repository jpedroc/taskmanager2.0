package br.gov.basis.taskmanager.taskservice.service;

import br.gov.basis.taskmanager.taskservice.domain.StatusTask;
import br.gov.basis.taskmanager.taskservice.domain.Task;
import br.gov.basis.taskmanager.taskservice.domain.elastic.TaskDocument;
import br.gov.basis.taskmanager.taskservice.repository.TaskRepository;
import br.gov.basis.taskmanager.taskservice.repository.elastic.TaskSearchRepository;
import br.gov.basis.taskmanager.taskservice.service.dto.TaskDTO;
import br.gov.basis.taskmanager.taskservice.service.dto.listing.TaskListDTO;
import br.gov.basis.taskmanager.taskservice.service.event.TaskEvent;
import br.gov.basis.taskmanager.taskservice.service.exception.BadRequestAlertException;
import br.gov.basis.taskmanager.taskservice.service.filter.TaskFilter;
import br.gov.basis.taskmanager.taskservice.service.mapper.TaskListMapper;
import br.gov.basis.taskmanager.taskservice.service.mapper.TaskMapper;
import br.gov.nuvem.comum.microsservico.web.rest.errors.ParametrizedMessageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskListMapper taskListMapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final TaskSearchRepository taskSearchRepository;
    private final Integer STATUS_ARCHIVED = 5;

    public void archivedTask(Integer taskId) {
        log.info("Archiving task: {}", taskId);
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ParametrizedMessageException("task-not-found", "Task not found"));
        task.setStatus(new StatusTask(STATUS_ARCHIVED));

        // TODO send email to notify owner

        taskRepository.save(task);
    }

    public TaskDTO save(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);

        if(Objects.nonNull(task.getStartDate()) && Objects.nonNull(task.getEndDate()) && task.getStartDate().isAfter(task.getEndDate())) {
            throw new BadRequestAlertException("Invalid expected date! Start date cannot be after the end date.");
        }

        taskRepository.save(task);
        this.applicationEventPublisher.publishEvent(new TaskEvent(task.getId()));
        return taskMapper.toDto(task);
    }

    public TaskDTO findOne(Integer id) {
        return taskMapper.toDto(getOne(id));
    }

    public void delete(Integer id) {
        Task task = getOne(id);
        taskRepository.delete(task);
    }

    private Task getOne(Integer id) {
        return taskRepository.findById(id).orElseThrow(() -> new BadRequestAlertException("Task not found"));
    }

    public List<TaskDTO> findAll() {
        return taskMapper.toDto(taskRepository.findAll());
    }

    public Page<TaskListDTO> search(TaskFilter filter, Pageable pageable) {
        Page<TaskDocument> documents = taskSearchRepository.search(filter.getFilter(), pageable);

        return documents.map(taskListMapper::toDto);
    }

}
