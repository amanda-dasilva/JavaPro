package br.com.mentorama.homework;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    StudentService studentService = new StudentService();

    @GetMapping
    public List<Student> listByNameOrAge(@RequestParam(required = false) String name, Integer age) throws StudentNotFoundException {
        return studentService.listByNameOrAge(name, age);
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") Integer id) throws StudentNotFoundException {
        return studentService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Integer> addNewStudent(@RequestBody final Student student) {
        return studentService.addNewStudent(student);
    }

    @PutMapping
    public ResponseEntity updateStudent(@RequestBody final Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") Integer id) {
        return studentService.deleteStudent(id);
    }
}
