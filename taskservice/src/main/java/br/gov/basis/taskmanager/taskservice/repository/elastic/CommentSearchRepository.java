package br.gov.basis.taskmanager.taskservice.repository.elastic;


import br.gov.basis.taskmanager.taskservice.domain.elastic.CommentDocument;

public interface CommentSearchRepository extends ElasticEntity<CommentDocument, Integer> {

    @Override
    default Class<CommentDocument> getEntityClass() {
        return CommentDocument.class;
    }
}
