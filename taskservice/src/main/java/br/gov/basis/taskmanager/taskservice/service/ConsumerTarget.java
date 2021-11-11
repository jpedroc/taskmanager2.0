package br.gov.basis.taskmanager.taskservice.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerTarget {

    String BINDING_CLOSE_TASKS = "close-task";

    @Input(ConsumerTarget.BINDING_CLOSE_TASKS)
    SubscribableChannel closeTask();

}
