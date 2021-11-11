package br.gov.basis.taskmanager.taskservice.service;

import br.gov.basis.taskmanager.taskservice.domain.StatusTask;
import br.gov.basis.taskmanager.taskservice.domain.Task;
import br.gov.basis.taskmanager.taskservice.repository.TaskRepository;
import br.gov.nuvem.comum.microsservico.web.rest.errors.ParametrizedMessageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final Integer STATUS_ARCHIVED = 5;

    public void archivedTask(Integer taskId) {
        log.info("Archiving task: {}", taskId);
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ParametrizedMessageException("task-not-found", "Task not found"));
        task.setStatus(new StatusTask(STATUS_ARCHIVED));

        // TODO send email to notify owner

        taskRepository.save(task);
    }

}
