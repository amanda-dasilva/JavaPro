package br.com.mentorama.helloworld.controllers;


import br.com.mentorama.helloworld.entities.Message;
import br.com.mentorama.helloworld.services.HelloWorldService;
import br.com.mentorama.helloworld.services.IHelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/helloworld")
public class HelloWorldController {

    private final List<Message> messages;

     //you can also put it as a parameter in the constructor method or @Autowired
    private final IHelloWorldService helloWorldService;

    public HelloWorldController(IHelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
        this.messages = new ArrayList<>();
    }

    @GetMapping
    public List<Message> findAll(@RequestParam(required = false) String message) {
        return helloWorldService.findAll(message);
    }

    @GetMapping("/{id}")
    public Message findById(@PathVariable("id") Integer id) {
        return helloWorldService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody final Message message) {
        Integer id = helloWorldService.add(message);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody final Message message) {
        helloWorldService.update(message);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        helloWorldService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
