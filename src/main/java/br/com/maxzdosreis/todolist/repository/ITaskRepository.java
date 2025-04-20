package br.com.maxzdosreis.todolist.repository;

import br.com.maxzdosreis.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ITaskRepository extends JpaRepository<Task, UUID> {
    Task findByTitle(String title);
    List<Task> findByIdUser(UUID IdUser);
}
