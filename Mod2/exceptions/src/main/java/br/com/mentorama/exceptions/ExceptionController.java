package br.com.mentorama.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exceptions")
public class ExceptionController {

//    final static Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

//    @GetMapping("/get1")
//    public ResponseEntity<String> get1() {
////        try {
////            throw new RuntimeException("Error occurred");
////        }
////        catch (Exception e) {
////            LOGGER.error(e.getMessage());
////        }
//////        LOGGER.info("Executed findAll method");
////        return "it's ok";
//        try {
//            throw new ResourceNotFoundException();
//        }
//        catch (ResourceNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/get1")
    public String get1() {
        throw new ResourceNotFoundException();
    }

    @GetMapping("/get2")
    public String get2() {
        throw new ResourceNotFoundException();
    }
}
