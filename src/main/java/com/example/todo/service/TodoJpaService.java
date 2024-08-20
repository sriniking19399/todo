/*
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.todo.repository.*;
import com.example.todo.model.Todo;

@Service
public class TodoJpaService implements TodoRepository {

    @Autowired
    private TodoJpaRepository todoRepository;

    @Override
    public ArrayList<Todo> getAllTodo() {
        List<Todo> todoList = todoRepository.findAll();
        ArrayList<Todo> todos = new ArrayList<>(todoList);
        return todos;
    }

    @Override
    public Todo getTodoById(int id) {
        try {
            Todo todo = todoRepository.findById(id).get();
            return todo;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Todo addTodo(Todo todo) {
        todoRepository.save(todo);
        return todo;
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
        try {
            Todo newTodo = todoRepository.findById(id).get();
            if (todo.getTodo() != null) {
                newTodo.setTodo(todo.getTodo());
            }
            if (todo.getPriority() != null) {
                newTodo.setPriority(todo.getPriority());
            }
            if (todo.getStatus() != null) {
                newTodo.setStatus(todo.getStatus());
            }
            todoRepository.save(newTodo);
            return newTodo;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteTodo(int id) {
        try {
            todoRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}
