package br.com.mentorama.helloworld.entities;

public abstract class Animal {

    public String name;
    public String species;

    public Animal(final String name, final String species) {
        this.name = name;
        this.species = species;
    }
}
