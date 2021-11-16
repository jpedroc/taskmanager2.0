package br.gov.basis.taskmanager.taskservice.service.mapper;

import br.gov.basis.taskmanager.taskservice.domain.Owner;
import br.gov.basis.taskmanager.taskservice.service.dto.OwnerDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OwnerMapper extends EntityMapper<OwnerDTO, Owner> {

    @Override
    @Mapping(source = "statusId", target = "status.id")
    Owner toEntity(OwnerDTO ownerDTO);

    @Override
    @InheritInverseConfiguration
    OwnerDTO toDto(Owner owner);
}
