package guru.springframework.controllers;

import guru.springframework.domain.Todo;
import guru.springframework.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TodoController {

    private TodoService todoService;

    @Autowired
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("todos", todoService.listAllTodos());
        return "todos";
    }

    @RequestMapping("todo/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        todoService.delete(id);
        return "redirect:/todos";
    }

    @RequestMapping(value = "todo", method = RequestMethod.POST)
    public String saveTodo(Todo todo){
        todoService.saveTodo(todo);
        return "redirect:/todos";
    }

}
