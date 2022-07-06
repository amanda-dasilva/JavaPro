package br.com.mentorama.helloworld.patterns;

import br.com.mentorama.helloworld.entities.Message;

public class Main {
    public static void main(String[] args) {
        Message msg = new MessageBuilder()
                .withId(1)
                .withMessage("Test")
                .withDescription("Description")
                .withAuthor("Author Test ")
                .build();
        System.out.println(msg);

        System.out.println(AnimalFactory.createAnimal("Cat", "Cat"));
    }
}
