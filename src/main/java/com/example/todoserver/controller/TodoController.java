package com.example.todoserver.controller;

import com.example.todoserver.domain.Todo;
import com.example.todoserver.service.TodoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getTodoList(){
        return todoService.getTodoList();
    }

    @PostMapping
    public void addTodo(@RequestBody Todo todo){
        todoService.addTodo(todo);
    }
}
