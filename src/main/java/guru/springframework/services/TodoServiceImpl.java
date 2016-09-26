package guru.springframework.services;

import guru.springframework.domain.Todo;
import guru.springframework.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRespo;

    @Autowired
    public void setTodoRespo(TodoRepository todoRespo) {
        this.todoRespo = todoRespo;
    }

    @Override
    public Iterable<Todo> listAllTodos() {
        return todoRespo.findAll();
    }

    @Override
    public Todo saveTodo(Todo todo) {
        todo.setDate(new Date());
        return todoRespo.save(todo);
    }

    @Override
    public void delete(Integer id) {
        todoRespo.delete(id);
    }
}
