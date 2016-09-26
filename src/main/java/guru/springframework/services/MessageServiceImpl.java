package guru.springframework.services;

import guru.springframework.domain.Message;
import guru.springframework.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Iterable<Message> listAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessageById(Integer id) {
        return messageRepository.findOne(id);
    }

    @Override
    public Message saveMessage(Message message) {
        message.setDate(new Date());
        return messageRepository.save(message);
    }

    @Override
    public void delete(Integer id) {
        messageRepository.delete(id);
    }
}
