package com.example.todoserver.controller;

import com.example.todoserver.domain.Todo;
import com.example.todoserver.service.TodoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public Todo getTodoList(@PathVariable Long id){
        return todoService.getTodo(id);
    }

    @PostMapping
    public void addTodo(@RequestBody Todo todo){
        todoService.addTodo(todo);
    }

    @PutMapping("/{id}")
    public void updateTodo(@PathVariable Long id, @RequestBody Todo todo){
        todoService.modifyTodo(id, todo);
    }

    @DeleteMapping("/{id}")
    public void removeTodo(@PathVariable Long id){
        todoService.deleteById(id);
    }
}
