package br.com.mentorama.helloworld.entities;

public class Dog extends Animal {
    public Dog(String name, String species) {
        super(name, species);
        System.out.println("Bark");
    }
}
