package com.br.rodrigofc.more_learning_API.controllers;

import com.br.rodrigofc.more_learning_API.models.CourseEntity;
import com.br.rodrigofc.more_learning_API.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCourse(@RequestBody CourseEntity course){

        try{
            var resultTransaction = this.courseService.create(course);
            return ResponseEntity.ok().body(resultTransaction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
