package com.mikulex.atda;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Controller
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskModelAssembler taskAssembler;

    TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    CollectionModel<EntityModel<Task>> all(){

        List<EntityModel<Task>> tasks = taskRepository.findAll().stream().map(taskAssembler::toModel).collect(Collectors.toList());
    }

    @GetMapping("/tasks/{id}")
    EntityModel<Task> one(@PathVariable Long id){

    }

    
}
