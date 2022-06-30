package br.com.mentorama.exceptions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloworld")
public class MessageController {

    @GetMapping
    public String helloworld () {
        throw new ResourceNotFoundException();
    }
}
