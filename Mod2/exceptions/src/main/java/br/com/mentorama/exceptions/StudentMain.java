package br.com.mentorama.exceptions;


public class StudentMain {
    public static void main(String[] args) {
//        StudentService studentService = new StudentService();
//        studentService.findALl().stream().forEach(System.out::println);
//        System.out.println("Success!!");
//        if(args.length > 0) {
//            String stringX = args[0];
//            System.out.println(stringX.contains("x"));
//        }
//        else {
//            System.out.println("There is nothing to show up");
//        }
        try {
            String student = new StudentService().findStudent("student 1");
            System.out.println(student);
        } catch (StudentNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
