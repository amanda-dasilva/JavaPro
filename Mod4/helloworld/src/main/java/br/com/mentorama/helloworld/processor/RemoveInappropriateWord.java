package br.com.mentorama.helloworld.processor;

import br.com.mentorama.helloworld.entities.Message;
import org.springframework.stereotype.Component;

@Component
public class RemoveInappropriateWord implements IProcessor{

    @Override
    public void process(final Message message) {
        System.out.println("Removing Inappropriate Words");
    }
}
