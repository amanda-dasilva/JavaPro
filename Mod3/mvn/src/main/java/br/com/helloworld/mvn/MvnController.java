package br.com.helloworld.mvn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloworld")
public class MvnController {
    @GetMapping
    public String getMvn() {

        String message = "Hello World!";
        return message;
    }
}
