package br.com.mentorama.homework;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class HomeworkController {
    private final List<Student> students;

    public HomeworkController(){
        this.students = new ArrayList<>();
    }
    @GetMapping
    public List<Student> listByNameOrAge(@RequestParam(required = false) String name, Integer age) {
        if(name != null) {
            return this.students.stream()
                    .filter(std -> std.getName().contains(name))
                    .collect(Collectors.toList());
        }
        else if(age != null) {
            return this.students.stream()
                    .filter(std -> std.getAge().equals(age))
                    .collect(Collectors.toList());
        }
        else {
            return students;
        }

    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") Integer id) {
        return this.students.stream()
                .filter(std -> std.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public ResponseEntity<Integer> addNewStudent(@RequestBody final Student student) {
        if(student.getId() == null) {
            student.setId(students.size() + 1);
        }
        students.add(student);
        return new ResponseEntity<>(student.getId(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateStudent(@RequestBody final Student student) {
        students.stream()
                .filter(std -> std.getId().equals(student.getId()))
                .forEach(std -> std.setName(student.getName()));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") Integer id) {
        students.removeIf(std -> std.getId().equals(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
