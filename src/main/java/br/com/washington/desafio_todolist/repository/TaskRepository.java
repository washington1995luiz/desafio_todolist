package br.com.washington.desafio_todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.washington.desafio_todolist.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{}
