package guru.springframework.bootstrap;

import guru.springframework.domain.Message;
import guru.springframework.domain.Todo;
import guru.springframework.repositories.MessageRepository;
import guru.springframework.repositories.TodoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MessageLoader implements ApplicationListener<ContextRefreshedEvent> {

    private MessageRepository messageRepository;
    private TodoRepository todoRepository;

    private Logger log = Logger.getLogger(MessageLoader.class);

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Message message = new Message();
        message.setUserName("张三");
        message.setContent("测试内容1");
        message.setDate(new Date());
        messageRepository.save(message);

        log.info("Saved test1 - id: " + message.getId());

        Todo todo = new Todo();
        todo.setContent("study Spring Boot");
        todo.setDate(new Date());
        todoRepository.save(todo);

        log.info("Saved todo1 - id:" + todo.getId());
    }
}
