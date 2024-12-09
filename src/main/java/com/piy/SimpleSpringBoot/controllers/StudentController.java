package com.piy.SimpleSpringBoot.controllers;

import com.piy.SimpleSpringBoot.beans.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class StudentController {

    private List<Student> studentList = new ArrayList<>();

    public StudentController() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        studentList.add((Student) applicationContext.getBean("Student1"));
        studentList.add((Student) applicationContext.getBean("Student2"));
        studentList.add((Student) applicationContext.getBean("Student3"));
    }

    @GetMapping("/getStudents")
    public List<Student> getStudents(){
        return studentList;
    }

    @GetMapping("/getStudent/{firstName}/{lastName}")
    public Student getStudent(@PathVariable("firstName")String firstName,
                                    @PathVariable("lastName")String lastName){

        return new Student(firstName, lastName);
    }

    @GetMapping("/getStudent/query")
    public Student getStudentUsingQueryParam(@RequestParam(name="firstName") String firstName,
                                             @RequestParam(name="lastName") String lastName){

        return new Student(firstName, lastName);
    }

    @PostMapping("/addStudent")
    public void addStudent(@RequestBody Student student){
        studentList.add(student);
    }

    //Replacing the student with specified firstName with the new student
    @PutMapping("/updateStudent/{firstNameOfStudentToBeReplaced}")
    public void updateStudent(@PathVariable("firstNameOfStudentToBeReplaced") String firstNameOfStudentToBeReplaced, @RequestBody Student newStudent){

        for(Student student : getStudentList()){
            if(student.getFirstName().equalsIgnoreCase(firstNameOfStudentToBeReplaced)){
                student.setFirstName(newStudent.getFirstName());
                student.setLastName(newStudent.getLastName());
            }
        }
    }

    @DeleteMapping("/removeStudent/{firstNameOfStudentToBeRemoved}")
    public void deleteStudent(@PathVariable("firstNameOfStudentToBeRemoved") String firstNameOfStudentToBeRemoved){
        for (Student student: getStudents()){
            if(student.getFirstName().equalsIgnoreCase(firstNameOfStudentToBeRemoved)) {
                studentList.remove(student);
                break;
            }
        }
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudentController that = (StudentController) o;
        return Objects.equals(studentList, that.studentList);

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(studentList);
    }

    @Override
    public String toString() {
        return "StudentController{" +
                "studentList=" + studentList +
                '}';
    }
}
