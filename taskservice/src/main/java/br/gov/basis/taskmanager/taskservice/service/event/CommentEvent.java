package br.gov.basis.taskmanager.taskservice.service.event;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommentEvent extends DefaultEvent {
    public CommentEvent(Integer id) {
        super(id);
    }
}
