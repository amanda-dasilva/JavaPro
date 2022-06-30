package br.com.mentorama.exceptions;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(final String name) {
        super("Student " + name + " not found!");
    }
}
