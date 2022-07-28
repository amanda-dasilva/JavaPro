package org.example;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MessageRepository messageRepository = new MessageRepository();
//        messageRepository.insertMessage(new Message(2L, "Primeira mensagem"));
//
        messageRepository.delete(2L);

        List<Message> messages = messageRepository.findAll();
        messages.stream().forEach(System.out::println);
    }
}
