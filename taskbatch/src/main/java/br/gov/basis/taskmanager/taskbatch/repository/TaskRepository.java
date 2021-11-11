package br.gov.basis.taskmanager.taskbatch.repository;

import br.gov.basis.taskmanager.taskbatch.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("select t from Task t where t.endDate is not null and t.status.id in (3, 4)")
    List<Integer> findAllTaskToClose();
}
