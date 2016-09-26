package guru.springframework.repositories;

import guru.springframework.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer>{
}
