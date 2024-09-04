package br.com.washington.desafio_todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.washington.desafio_todolist.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
}
