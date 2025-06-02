package com.br.rodrigofc.more_learning_API.controllers;

import com.br.rodrigofc.more_learning_API.dtos.CourseResponseDTO;
import com.br.rodrigofc.more_learning_API.dtos.CourseUpdateDTO;
import com.br.rodrigofc.more_learning_API.exceptions.CourseNotFound;
import com.br.rodrigofc.more_learning_API.models.CourseEntity;
import com.br.rodrigofc.more_learning_API.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courses")
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

    @GetMapping("")
    public  ResponseEntity<Object> listCourses(){

        try{
            List<CourseEntity> courseEntityList = courseService.getAll();
            return ResponseEntity.ok().body(courseEntityList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> specificCourse(@PathVariable String id){
        try{

            UUID courseId = UUID.fromString(id);

            var course = courseService.getOne(courseId);

            return ResponseEntity.ok().body(course);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error_message: " + e.getMessage());
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateCourse(@PathVariable String id, @RequestBody CourseUpdateDTO dataToBeUpdated){

        try{
            UUID courseId = UUID.fromString(id);

            CourseResponseDTO responseData = courseService.applyPatchUpdate(courseId, dataToBeUpdated);

            return  ResponseEntity.status(HttpStatus.OK).body(responseData);
        } catch (CourseNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
