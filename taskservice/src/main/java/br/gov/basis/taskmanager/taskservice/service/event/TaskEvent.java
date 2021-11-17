package br.gov.basis.taskmanager.taskservice.service.event;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaskEvent extends DefaultEvent {
    public TaskEvent(Integer id) {
        super(id);
    }
}
