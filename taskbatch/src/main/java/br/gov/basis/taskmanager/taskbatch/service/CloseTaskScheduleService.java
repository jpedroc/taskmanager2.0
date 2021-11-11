package br.gov.basis.taskmanager.taskbatch.service;

import br.gov.basis.taskmanager.taskbatch.repository.TaskRepository;
import br.gov.basis.taskmanager.taskbatch.service.rabbitmq.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableRetry
@Transactional
public class CloseTaskScheduleService {
    private final TaskRepository taskRepository;
    private final ProducerService producerService;

    @Scheduled(cron = "${application.cron.close-task-cron}")
    @Retryable(value = Exception.class, maxAttemptsExpression = "${application.try}")
    public void closeTasks() {
        log.info("Starting search for tasks that need to be closed");
        List<Integer> allTaskToClose = taskRepository.findAllTaskToClose();

        if(allTaskToClose.isEmpty()) {
            return;
        }

        allTaskToClose.forEach(taskId -> {
            log.info("Send Task {} to close.", taskId);
            producerService.closeTask(taskId);
        });
        log.info("All tasks have been sent to be closed");
    }
}
