package com.example.todoserver.service;

import com.example.todoserver.domain.Todo;
import com.example.todoserver.entity.TodoEntity;
import com.example.todoserver.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Todo getTodo(Long id){
        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException());
        return toUser(todoEntity);
    }

    public void addTodo(Todo todo){
        TodoEntity toSave = toPersist(null, todo);

        todoRepository.save(toSave);
    }

    public void modifyTodo(Long id, Todo todo){
        TodoEntity toSave = todoRepository.findById(id)
                        .orElseThrow(()->new EntityNotFoundException());
        todo.setId(toSave.getId());
        todo.setCreatedAt(toSave.getCreatedAt());
        toSave = toPersist(toSave, todo);
        toSave.setUpdatedAt(LocalDateTime.now());

        todoRepository.save(toSave);
    }

    public Todo toUser(TodoEntity todoEntity){
        return new Todo(todoEntity.getId(), todoEntity.getTitle(), todoEntity.getContent(), todoEntity.getCreatedAt(), todoEntity.getUpdatedAt());
    }

    public TodoEntity toPersist(TodoEntity exist, Todo todo){
        if(exist == null){
            exist = new TodoEntity();
        }

        exist.setId(todo.getId());
        exist.setTitle(todo.getTitle());
        exist.setContent(todo.getContent());

        if (todo.getCreatedAt() == null) {
            exist.setCreatedAt(LocalDateTime.now());
        }

        if (todo.getUpdatedAt() == null) {
            exist.setUpdatedAt(LocalDateTime.now());
        }

        return exist;
    }
}
