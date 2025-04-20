package br.com.maxzdosreis.todolist.controller;

import br.com.maxzdosreis.todolist.model.Task;
import br.com.maxzdosreis.todolist.repository.ITaskRepository;
import br.com.maxzdosreis.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private ITaskRepository taskRepository;

    public TaskController(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Task task, HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        task.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();

        var titulo = taskRepository.findByTitle(task.getTitle());

        // Implementar a validação de não deixar cadastrar uma tarefa igual para o mesmo usuário, mas deve permitir se for outro usuário diferenteç

        if(currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início / data de término deve ser maior que a data atual.");
        }

        if(task.getStartAt().isAfter(task.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor do que a data de término.");
        }

        var tarefa = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
    }

    @GetMapping("/")
    public List<Task> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Task task, HttpServletRequest request, @PathVariable UUID id) {
        var tarefa = taskRepository.findById(id).orElse(null);

        if(tarefa == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não encontrada");
        }

        var idUser = request.getAttribute("idUser");

        if(!tarefa.getIdUser().equals(idUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não tem permissão para alterar essa tarefa");
        }

        // Forma para que consigam realizar um update parcial, se algumas das variáveis ficarem como null
        Utils.copyNonNullProperties(task, tarefa);

        var taskUpdated = taskRepository.save(tarefa);
        return ResponseEntity.status(HttpStatus.OK).body(taskUpdated);
    }
}
