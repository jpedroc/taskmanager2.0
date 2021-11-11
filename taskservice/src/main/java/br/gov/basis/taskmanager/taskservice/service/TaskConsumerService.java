package br.gov.basis.taskmanager.taskservice.service;

import br.gov.basis.taskmanager.taskservice.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
@EnableBinding(ConsumerTarget.class)
public class TaskConsumerService {

    private final TaskService taskService;

    @StreamListener(target = ConsumerTarget.BINDING_CLOSE_TASKS)
    private void archivingTask(@Payload Integer taskId) {

        log.info("Event to close the task: {}", taskId);

        if(Objects.isNull(taskId)) {
            return;
        }

        taskService.archivedTask(taskId);

    }

}
