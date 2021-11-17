package br.gov.basis.taskmanager.taskservice.service.mapper;

import br.gov.basis.taskmanager.taskservice.domain.elastic.CommentDocument;
import br.gov.basis.taskmanager.taskservice.service.dto.listing.CommentListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentListMapper extends EntityMapper<CommentListDTO, CommentDocument> {

}
