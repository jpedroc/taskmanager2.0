package br.gov.basis.taskmanager.taskservice.repository.elastic;


import br.gov.basis.taskmanager.taskservice.domain.elastic.TaskDocument;

public interface TaskSearchRepository extends ElasticEntity<TaskDocument, Integer> {

    @Override
    default Class<TaskDocument> getEntityClass() {
        return TaskDocument.class;
    }
}

