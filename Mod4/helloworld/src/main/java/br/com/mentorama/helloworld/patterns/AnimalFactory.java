package br.com.mentorama.helloworld.patterns;

import br.com.mentorama.helloworld.entities.Animal;
import br.com.mentorama.helloworld.entities.Cat;
import br.com.mentorama.helloworld.entities.Dog;

public class AnimalFactory {

    public static Animal createAnimal(final String name, final String species) {
        if(species.equals("Cat")) {
            return new Cat(name, species);
        }else if(species.equals("Dog")){
            return new Dog(name, species);
        }
        throw new RuntimeException("Invalid species of existed animals");
    }
}
