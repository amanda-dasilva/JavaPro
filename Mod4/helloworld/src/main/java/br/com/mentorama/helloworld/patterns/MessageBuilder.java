package br.com.mentorama.helloworld.patterns;

import br.com.mentorama.helloworld.entities.Message;

public class MessageBuilder {

    private Integer id;
    private String message;
    private String author;
    private String description;

    public MessageBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public MessageBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public MessageBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public MessageBuilder withDescription(String description) {
        this.description = description;
        return this;
    }
    public Message build() {
        return new Message(id, message, author, description);
    }

}
