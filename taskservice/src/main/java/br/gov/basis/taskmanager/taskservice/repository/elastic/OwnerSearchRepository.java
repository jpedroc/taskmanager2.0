package br.gov.basis.taskmanager.taskservice.repository.elastic;

import br.gov.basis.taskmanager.taskservice.domain.elastic.OwnerDocument;

public interface OwnerSearchRepository extends ElasticEntity<OwnerDocument, Integer> {

    @Override
    default Class<OwnerDocument> getEntityClass() {
        return OwnerDocument.class;
    }
}
