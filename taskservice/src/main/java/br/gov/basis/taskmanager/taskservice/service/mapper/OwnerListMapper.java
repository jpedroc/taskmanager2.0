package br.gov.basis.taskmanager.taskservice.service.mapper;

import br.gov.basis.taskmanager.taskservice.domain.elastic.OwnerDocument;
import br.gov.basis.taskmanager.taskservice.service.dto.listing.OwnerListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerListMapper extends EntityMapper<OwnerListDTO, OwnerDocument> {
}
