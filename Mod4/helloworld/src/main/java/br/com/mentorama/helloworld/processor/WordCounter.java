package br.com.mentorama.helloworld.processor;

import br.com.mentorama.helloworld.entities.Message;
import org.springframework.stereotype.Component;

@Component
public class WordCounter implements IProcessor{

    @Override
    public void process(final Message message) {
        System.out.println("Word Counter " + 10);
    }
}
