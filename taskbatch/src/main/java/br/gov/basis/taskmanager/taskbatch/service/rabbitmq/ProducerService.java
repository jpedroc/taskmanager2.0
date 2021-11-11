package br.gov.basis.taskmanager.taskbatch.service.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@EnableBinding(ProducerSource.class)
@Service
@RequiredArgsConstructor
public class ProducerService {

    private final ProducerSource producerSource;

    public void closeTask(Integer taskId) {
        Message<Integer> message = MessageBuilder.withPayload(taskId).build();
        producerSource.closeTasks().send(message);
    }

}
