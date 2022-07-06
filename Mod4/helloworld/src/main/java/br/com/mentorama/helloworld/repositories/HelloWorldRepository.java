package br.com.mentorama.helloworld.repositories;

import br.com.mentorama.helloworld.entities.Message;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class HelloWorldRepository {

    private static HelloWorldRepository helloWorldRepository;

    public static HelloWorldRepository getInstance() {
        if(helloWorldRepository == null){
            helloWorldRepository = new HelloWorldRepository();
        }
        return helloWorldRepository;
    }

    private final List<Message> messages;

//    private HelloWorldRepository(List<Message> messages) {
//        this.messages = messages;
//    }
    private HelloWorldRepository() {
        this.messages = new LinkedList<>();
    }

    public List<Message> findAll(){
        return messages;
    }
    public List<Message> findAll(final String message){
        return messages.stream()
                .filter(msg -> msg.getMessage().contains(message))
                .collect(Collectors.toList());
    }
    public Message findById(Integer id){
        return this.messages.stream()
                .filter(msg -> msg.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public void add(Message message){
        this.messages.add(message);
    }
    public int count(){
        return messages.size();
    }
    public void update(final Message message) {
        messages.stream()
                .filter(msg -> msg.getId().equals(message.getId()))
                .forEach(msg -> msg.setMessage(message.getMessage()));
    }

    public void delete(@PathVariable("id") Integer id) {
        messages.removeIf(msg -> msg.getId().equals(id));
    }
}
