package com.br.rodrigofc.more_learning_API.unit;

import com.br.rodrigofc.more_learning_API.models.CourseEntity;
import com.br.rodrigofc.more_learning_API.repositories.CourseRepository;
import com.br.rodrigofc.more_learning_API.services.CourseService;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath.application-test.properties")
public class CourseServicerTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @Test
    @DisplayName("Should create a instance of Course")
    public void shouldCreateCourserWithSuccess(){
        CourseEntity entityTest = CourseEntity.builder().name("Course Test").category("test").description("test").id(UUID.randomUUID()).build();

        when(courseRepository.save(any(CourseEntity.class))).thenReturn(entityTest);

        CourseEntity test = new CourseEntity();
        CourseEntity testObject = courseService.create(test);

        Assertions.assertNotNull(testObject);
        Assertions.assertEquals("Course Test", testObject.getName());
        verify(courseRepository, times(1)).save(any(CourseEntity.class));
    }
}
