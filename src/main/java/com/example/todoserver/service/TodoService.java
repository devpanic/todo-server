package com.example.todoserver.service;

import com.example.todoserver.domain.Todo;
import com.example.todoserver.entity.TodoEntity;
import com.example.todoserver.repository.TodoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodoList(){
        return todoRepository.findAll().stream().map(
                todoEntity ->  new Todo(todoEntity.getId(),todoEntity.getTitle(),todoEntity.getContent(),todoEntity.getCreatedAt(),todoEntity.getUpdatedAt())).toList();
    }

    public void addTodo(Todo todo){
        TodoEntity toSave = new TodoEntity();

        toSave.setId(todo.getId());
        toSave.setTitle(todo.getTitle());
        toSave.setContent(todo.getContent());

        if (todo.getCreatedAt() == null) {
            toSave.setCreatedAt(LocalDateTime.now());
        }

        if (todo.getUpdatedAt() == null) {
            toSave.setUpdatedAt(LocalDateTime.now());
        }

        todoRepository.save(toSave);
    }
}
