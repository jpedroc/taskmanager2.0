package br.gov.basis.taskmanager.taskservice.repository;

import br.gov.basis.taskmanager.taskservice.domain.Comment;
import br.gov.basis.taskmanager.taskservice.domain.elastic.CommentDocument;
import br.gov.nuvem.comum.microsservico.repository.Reindexador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>, Reindexador {
    List<Comment> findAllByTaskId(Integer taskId);

    @Query(value = "select new br.gov.basis.taskmanager.taskservice.domain.elastic.CommentDocument( " +
            "c.id, c.description, c.task.title, c.owner.name) from Comment c where c.id = :id")
    CommentDocument getDocument(@Param("id") Integer id);

    @Override
    @Query("select new br.gov.basis.taskmanager.taskservice.domain.elastic.CommentDocument(c.id, c.description, c.task.title, c.owner.name) " +
        "from Comment c order by c.id")
    Page<CommentDocument> reindexPage(Pageable pageable);

}
