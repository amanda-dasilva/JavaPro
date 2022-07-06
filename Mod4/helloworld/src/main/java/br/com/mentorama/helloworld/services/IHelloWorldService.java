package br.com.mentorama.helloworld.services;

import br.com.mentorama.helloworld.entities.Message;

import java.util.List;

public interface IHelloWorldService  {

    List<Message> findAll(String message);
    Message findById(Integer id);

    Integer add(final Message message);
    void update(final Message message);
    void delete(Integer id);

}
