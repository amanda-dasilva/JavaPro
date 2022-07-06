package br.com.mentorama.helloworld.processor;

import br.com.mentorama.helloworld.entities.Message;

public interface IProcessor {

    void process(Message message);
}
