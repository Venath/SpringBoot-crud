package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudenrService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudenrService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional= studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        System.out.println("Saving student: " + student);
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
       boolean exists= studentRepository.existsById(studentId);
       if(!exists){
           throw new IllegalStateException("student with is" + studentId + " not found");
       }
       studentRepository.deleteById(studentId);
    }

    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " not found"));

        boolean isUpdated = false;

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
            isUpdated = true;
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
            isUpdated = true;
        }

        if (isUpdated) {
            studentRepository.save(student); // Save changes if updated
        }
    }


}
