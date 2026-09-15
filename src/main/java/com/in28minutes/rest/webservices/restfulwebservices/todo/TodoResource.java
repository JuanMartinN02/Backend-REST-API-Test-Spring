package com.in28minutes.rest.webservices.restfulwebservices.todo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {

    private TodoService todoService;


    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodosById(@PathVariable String username){
        return todoService.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodosById(@PathVariable int id){
        return todoService.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public void deleteTodoById(@PathVariable int id){
        todoService.deleteById(id);
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@RequestBody Todo todo){
        todoService.updateTodo(todo);

        return todo;
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo){
        Todo createdTodo = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());

        return createdTodo;
    }


}
