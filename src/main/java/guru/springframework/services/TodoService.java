package guru.springframework.services;


import guru.springframework.domain.Todo;

public interface TodoService {
    Iterable<Todo> listAllTodos();

    Todo saveTodo(Todo todo);

    void delete(Integer id);
}
