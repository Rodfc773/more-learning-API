package com.br.rodrigofc.more_learning_API.services;

import com.br.rodrigofc.more_learning_API.models.CourseEntity;
import com.br.rodrigofc.more_learning_API.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public CourseEntity create(CourseEntity entity){
        return this.repository.save(entity);
    }

    public List<CourseEntity> getAll(){

        try {
            return this.repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong");
        }
    }
}
