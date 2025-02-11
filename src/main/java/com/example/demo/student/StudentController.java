package com.example.demo.student;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudenrService studenrService;

    public StudentController(StudenrService studenrService) {
        this.studenrService = studenrService;
    }

    @GetMapping
    public List<Student> getStudent(){
        return studenrService.getStudent();
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studenrService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studenrService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) String email)
    {
        studenrService.updateStudent(studentId, name, email);
    }
}