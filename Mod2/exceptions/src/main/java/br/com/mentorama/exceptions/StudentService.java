package br.com.mentorama.exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class StudentService {

    public List<String> findAll() {
        String path = StudentService.class.getClassLoader()
                .getResource("student.txt")
                .getPath();
        try {
            List<String> students = Files.readAllLines(Path.of(path));
            return students;
        }
        catch (IOException ex) {
//            return Collections.emptyList();
            throw new RuntimeException("File not found");
        }
    }

    public String findStudent(String name) throws StudentNotFoundException {
        return  findAll().stream()
                .filter(std -> std.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() ->new StudentNotFoundException(name));
    }
}
