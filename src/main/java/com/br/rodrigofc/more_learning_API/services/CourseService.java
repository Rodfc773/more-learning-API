package com.br.rodrigofc.more_learning_API.services;

import com.br.rodrigofc.more_learning_API.dtos.CourseResponseDTO;
import com.br.rodrigofc.more_learning_API.dtos.CourseUpdateDTO;
import com.br.rodrigofc.more_learning_API.models.CourseEntity;
import com.br.rodrigofc.more_learning_API.repositories.CourseRepository;
import com.br.rodrigofc.more_learning_API.exceptions.CourseNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public CourseResponseDTO create(CourseEntity entity){

        CourseEntity newCourse =  this.repository.save(entity);

        return CourseResponseDTO.builder()
                .courseName(newCourse.getName())
                .description(newCourse.getDescription())
                .id(newCourse.getId())
                .createdAt(newCourse.getCreated_at())
                .updateAt(newCourse.getUpdated_at())
                .categories(newCourse.getCategory()).build();
    }

    public List<CourseEntity> getAll(){

        try {
            return this.repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    public CourseResponseDTO getOne(UUID uuid){

        CourseEntity responseData = repository.findById(uuid).orElseThrow(CourseNotFound::new);

        return CourseResponseDTO.builder()
                .courseName(responseData.getName())
                .description(responseData.getDescription())
                .id(responseData.getId())
                .createdAt(responseData.getCreated_at())
                .updateAt(responseData.getUpdated_at())
                .categories(responseData.getCategory()).build();
    }

    public CourseResponseDTO applyPatchUpdate(UUID targetId, CourseUpdateDTO dataToUpdate){

        CourseEntity course = repository.findById(targetId).orElseThrow(CourseNotFound::new);

        dataToUpdate.getName().ifPresent(course::setName);
        dataToUpdate.getCategories().ifPresent(course::setCategory);
        dataToUpdate.getIsActive().ifPresent(course::setActive);
        dataToUpdate.getDescription().ifPresent(course::setDescription);

        repository.save(course);

        return CourseResponseDTO.builder()
                .courseName(course.getName())
                .description(course.getDescription())
                .id(course.getId())
                .createdAt(course.getCreated_at())
                .updateAt(course.getUpdated_at())
                .categories(course.getCategory()).build();
    }

    public CourseResponseDTO deleteCourse(UUID targetId){

        CourseEntity course = repository.findById(targetId).orElseThrow(CourseNotFound::new);

        repository.deleteById(course.getId());

        return CourseResponseDTO.builder()
                .courseName(course.getName())
                .description(course.getDescription())
                .id(course.getId())
                .createdAt(course.getCreated_at())
                .updateAt(course.getUpdated_at())
                .categories(course.getCategory()).build();
    }
}
