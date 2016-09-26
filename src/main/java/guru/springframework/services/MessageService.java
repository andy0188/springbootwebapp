package guru.springframework.services;


import guru.springframework.domain.Message;

public interface MessageService {
    Iterable<Message> listAllMessages();

    Message getMessageById(Integer id);

    Message saveMessage(Message message);

    void delete(Integer id);
}
