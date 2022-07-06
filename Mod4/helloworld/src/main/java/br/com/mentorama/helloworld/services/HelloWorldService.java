package br.com.mentorama.helloworld.services;

import br.com.mentorama.helloworld.entities.Message;
import br.com.mentorama.helloworld.processor.IProcessor;
import br.com.mentorama.helloworld.repositories.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelloWorldService implements IHelloWorldService {

    private HelloWorldRepository helloWorldRepository = HelloWorldRepository.getInstance();

    @Autowired
    List<IProcessor> processor;


    public List<Message> findAll(String message) {
        if(message != null) {
            return helloWorldRepository.findAll(message);
        }
        return helloWorldRepository.findAll();
    }
    public Message findById(Integer id) {
        return helloWorldRepository.findById(id);
    }


    public Integer add(final Message message) {
        if(message.getId() == null) {
            message.setId(helloWorldRepository.count() + 1);
        }
        processor.stream().forEach(processor -> processor.process(message));
        helloWorldRepository.add(message);
        return message.getId();
    }


    public void update(final Message message) {
        helloWorldRepository.update(message);
    }
    public void delete(@PathVariable("id") Integer id) {
        helloWorldRepository.delete(id);
    }
}
