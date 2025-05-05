package com.br.rodrigofc.more_learning_API.exceptions;

public class CourseNotFound extends RuntimeException{

    public CourseNotFound(){
        super("Course couldn't be find");
    }
}
