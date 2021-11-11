package br.gov.basis.taskmanager.taskbatch.service.rabbitmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerSource {

    String BINDING_CLOSE_TASKS = "close-tasks";

    @Output(ProducerSource.BINDING_CLOSE_TASKS)
    MessageChannel closeTasks();

}
