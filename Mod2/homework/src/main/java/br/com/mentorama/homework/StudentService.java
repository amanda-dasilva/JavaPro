package br.com.mentorama.homework;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {

    private final List<Student> students;

    public StudentService(){
        this.students = new ArrayList<>();
    }

    public List<Student> listByNameOrAge(@RequestParam(required = false) String name, Integer age) throws StudentNotFoundException {
        List<Student> studentList = students;
        if(name != null) {
            studentList = this.students.stream()
                    .filter(std -> std.getName().contains(name))
                    .collect(Collectors.toList());
            if(studentList.isEmpty()) {
                throw new StudentNotFoundException();
            }
        }
        if(age != null) {
            studentList =  this.students.stream()
                    .filter(std -> std.getAge().equals(age))
                    .collect(Collectors.toList());
            if(studentList.isEmpty()) {
                throw new StudentNotFoundException();
            }
        }

        return studentList;
    }
    public Student findById(@PathVariable("id") Integer id) throws StudentNotFoundException {
        return this.students.stream()
                .filter(std -> std.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException());
    }
    public ResponseEntity<Integer> addNewStudent(@RequestBody final Student student) {
        if(student.getId() == null) {
            student.setId(students.size() + 1);
        }
        students.add(student);
        return new ResponseEntity<>(student.getId(), HttpStatus.CREATED);
    }

    public ResponseEntity updateStudent(@RequestBody final Student student) {
        students.stream()
                .filter(std -> std.getId().equals(student.getId()))
                .forEach(std -> std.setName(student.getName()));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity deleteStudent(@PathVariable("id") Integer id) {
        students.removeIf(std -> std.getId().equals(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
