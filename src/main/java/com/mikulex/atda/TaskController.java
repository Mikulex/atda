package com.mikulex.atda;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskModelAssembler taskAssembler;

    public TaskController(TaskRepository taskRepository, TaskModelAssembler taskAssembler){
        this.taskRepository = taskRepository;
        this.taskAssembler = taskAssembler;
    }

    @GetMapping("/tasks")
    CollectionModel<EntityModel<Task>> all(){

        List<EntityModel<Task>> tasks = taskRepository.findAll().stream().map(taskAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(tasks, linkTo(methodOn(TaskController.class).all()).withSelfRel());
    }

    @GetMapping("/tasks/{id}")
    EntityModel<Task> one(@PathVariable Long id){

        Task task = taskRepository.findById(id).orElseThrow();
        return taskAssembler.toModel(task);
    }

    @PostMapping("/tasks")
    ResponseEntity<?> newTask(@RequestBody Task newTask) {
        EntityModel<Task> model = taskAssembler.toModel(taskRepository.save(newTask));

        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

    @PutMapping("/tasks/{id}")
    ResponseEntity<?> replaceTask(@RequestBody Task newTask, @PathVariable Long id) {
        
        Task replacedTask = taskRepository.findById(id)
        .map(task -> {
            task.setContent(newTask.getContent());
            task.setTitle(newTask.getTitle());
            task.setCheckList(newTask.getCheckList());
            return taskRepository.save(task);
        })
        .orElseGet(() -> {
            newTask.setId(id);
            return taskRepository.save(newTask);
        });

        EntityModel<Task> model = taskAssembler.toModel(replacedTask);

        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

    @DeleteMapping("/task/{id}")
    ResponseEntity<?> deleteTask(@PathVariable Long id) {

        taskRepository.deleteById(id);

        return ResponseEntity.noContent().build();
        
    }
    
}
