package br.com.maxzdosreis.todolist.repository;

import br.com.maxzdosreis.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
