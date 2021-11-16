package br.gov.basis.taskmanager.taskservice.service;

import br.gov.basis.taskmanager.taskservice.domain.Comment;
import br.gov.basis.taskmanager.taskservice.repository.CommentRepository;
import br.gov.basis.taskmanager.taskservice.repository.elastic.CommentSearchRepository;
import br.gov.basis.taskmanager.taskservice.service.dto.CommentDTO;
import br.gov.basis.taskmanager.taskservice.service.dto.listing.CommentListDTO;
import br.gov.basis.taskmanager.taskservice.service.event.CommentEvent;
import br.gov.basis.taskmanager.taskservice.service.exception.BadRequestAlertException;
import br.gov.basis.taskmanager.taskservice.service.filter.CommentFilter;
import br.gov.basis.taskmanager.taskservice.service.mapper.CommentListMapper;
import br.gov.basis.taskmanager.taskservice.service.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final CommentSearchRepository commentSearchRepository;
    private final CommentListMapper commentListMapper;

    public CommentDTO create(CommentDTO commentDTO){
        if(StringUtils.isBlank(commentDTO.getDescription())) {
            throw new BadRequestAlertException("Comment is not empty");
        }

        Comment save = commentRepository.save(commentMapper.toEntity(commentDTO));
        applicationEventPublisher.publishEvent(new CommentEvent(save.getId()));
        return commentMapper.toDto(save);
    }

    public void delete(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new BadRequestAlertException("Comment not found!"));

        commentRepository.delete(comment);
    }

    public List<CommentDTO> findAllByTask(Integer taskId) {
        return commentMapper.toDto(commentRepository.findAllByTaskId(taskId));
    }

    public Page<CommentListDTO> search(CommentFilter filter, Pageable pageable) {
        return commentSearchRepository.search(filter.getFilter(), pageable).map(commentListMapper::toDto);
    }

}
