package com.example.todocomponent;

import java.util.ArrayList;
import java.util.UUID;

public class TodoModel {

    private static TodoModel sTodoModel;

    private ArrayList<Todo> listTodos;

    public static TodoModel getInstance(){
        if(sTodoModel == null){
            sTodoModel = new TodoModel();
        }
            return sTodoModel;
    }

    private TodoModel(){
        listTodos = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Todo todo = new Todo();
            todo.setTitle("todo title: " + i);
            todo.setDetail("todo detail: "+ i);
            todo.setComplete(false);
            listTodos.add(todo);
        }

    }

    public ArrayList<Todo> getTodosList(){
        return listTodos;
    }

    public Todo getTodo(UUID id){
        for (Todo todo : listTodos){
            if(todo.getId().equals(id)){
                return todo;
            }
        }
        return null;
    }

    public void addTodo(Todo todo){
        listTodos.add(todo);
    }

    public void delete(UUID id){
        Todo todo = getTodo(id);
        listTodos.remove(todo);
    }


}
