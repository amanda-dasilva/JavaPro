package br.com.mentorama.helloworld.entities;

public class Message {

    private Integer id;

    private String message;

    private String author;

    private String description;

    public Message(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    public Message(Integer id, String message, String author, String description) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
