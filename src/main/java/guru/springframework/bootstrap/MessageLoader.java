package guru.springframework.bootstrap;

import guru.springframework.domain.Message;
import guru.springframework.repositories.MessageRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MessageLoader implements ApplicationListener<ContextRefreshedEvent> {

    private MessageRepository messageRepository;

    private Logger log = Logger.getLogger(MessageLoader.class);

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Message test1 = new Message();
        test1.setUserName("张三");
        test1.setContent("测试内容1");
        test1.setDate(new Date());
        messageRepository.save(test1);

        log.info("Saved test1 - id: " + test1.getId());

        Message test2 = new Message();
        test2.setUserName("李四");
        test2.setContent("测试内容2");
        test2.setDate(new Date());
        messageRepository.save(test2);

        log.info("Saved test2 - id:" + test2.getId());
    }
}
