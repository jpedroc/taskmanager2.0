package br.gov.basis.taskmanager.taskservice.repository;

import br.gov.basis.taskmanager.taskservice.domain.Owner;
import br.gov.basis.taskmanager.taskservice.domain.elastic.OwnerDocument;
import br.gov.nuvem.comum.microsservico.repository.Reindexador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer>, Reindexador {

    @Query(value = "select new br.gov.basis.taskmanager.taskservice.domain.elastic.OwnerDocument( " +
            " o.id, o.name, o.email, o.birthDate, o.status.description) from Owner o where o.id = :id")
    OwnerDocument getDocument(@Param("id") Integer id);

    @Override
    @Query(value = "select new br.gov.basis.taskmanager.taskservice.domain.elastic.OwnerDocument( " +
            " o.id, o.name, o.email, o.birthDate, o.status.description) from Owner o order by o.id")
    Page<OwnerDocument> reindexPage(Pageable pageable);
}
