package br.gov.basis.taskmanager.taskservice.repository;

import br.gov.basis.taskmanager.taskservice.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
