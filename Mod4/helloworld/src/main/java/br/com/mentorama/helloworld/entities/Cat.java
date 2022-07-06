package br.com.mentorama.helloworld.entities;

public class Cat extends Animal {
    public Cat(final String name, final  String species) {
        super(name, species);
        System.out.println("Meow");
    }
}
