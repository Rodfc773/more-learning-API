package com.br.rodrigofc.more_learning_API.unit;

import com.br.rodrigofc.more_learning_API.dtos.CourseResponseDTO;
import com.br.rodrigofc.more_learning_API.dtos.CourseUpdateDTO;
import com.br.rodrigofc.more_learning_API.models.CourseEntity;
import com.br.rodrigofc.more_learning_API.repositories.CourseRepository;
import com.br.rodrigofc.more_learning_API.services.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    protected CourseEntity createSut() {

        List<String> categories = new ArrayList<>();

        categories.add("test");

        return CourseEntity.builder().name("test").id(UUID.randomUUID()).category(categories).description("test").build();
    }

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a instance of Course")
    public void shouldCreateCourserWithSuccess(){

        List<String> categories = new ArrayList<>();

        categories.add("test");

        CourseEntity entityTest = CourseEntity.builder().name("Course Test").category(categories).description("test").id(UUID.randomUUID()).build();

        when(courseRepository.save(any(CourseEntity.class))).thenReturn(entityTest);

        CourseEntity test = new CourseEntity();
        CourseResponseDTO testObject = courseService.create(test);

        Assertions.assertNotNull(testObject);
        Assertions.assertEquals("Course Test", testObject.getCourseName());
        verify(courseRepository, times(1)).save(any(CourseEntity.class));
    }

    @Test
    @DisplayName("Should update a instance if that instance exists")
    public void shouldUpdateInstance(){

        var entityTest = createSut();

        CourseUpdateDTO courseUpdateDTO = CourseUpdateDTO.builder().name(Optional.of("teste 2")).description(Optional.of("teste teste")).build();

        when(courseRepository.findById(entityTest.getId())).thenReturn(Optional.of(entityTest));

        when(courseRepository.save(any(CourseEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));
        
        var updateCourse = courseService.applyPatchUpdate(entityTest.getId(), courseUpdateDTO);

        Assertions.assertNotNull(updateCourse);
        Assertions.assertEquals(entityTest.getId(), updateCourse.getId());
        Assertions.assertEquals("teste 2", updateCourse.getCourseName());
        Assertions.assertEquals("teste tese", updateCourse.getDescription());

        verify(courseRepository, times(1)).findById(any(UUID.class));
        verify(courseRepository, times(1)).save(any(CourseEntity.class));
    }
    @Test
    @DisplayName("Should delete a instance if that instance exists")
    public void shouldDeleteCourseInstance(){

        var entityTest = createSut();
        var courseId = entityTest.getId();

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(entityTest));

        courseService.deleteCourse(courseId);

        verify(courseRepository, times(1)).deleteById(any(UUID.class));

    }
    @Test
    @DisplayName("Should not create a course if the name was not sent")
    public void shouldNotCreateCourseWithoutName(){

        List<String> categories = new ArrayList<>();

        categories.add("test");

        CourseEntity entityTest = CourseEntity.builder().category(categories).description("test fail").id(UUID.randomUUID()).build();

        try {
            courseService.create(entityTest);
        } catch (Exception e) {
            Assertions.assertInstanceOf(Exception.class, e);
        }
    }

    @Test
    @DisplayName("Should not create a course if the category was not sent")
    public void shouldNotCreateCourseWithoutCategory(){

        CourseEntity entityTest = CourseEntity.builder().name("test fail").description("test fail").id(UUID.randomUUID()).build();
        try {
            courseService.create(entityTest);
        } catch (Exception e) {
            Assertions.assertInstanceOf(Exception.class, e);
        }
    }
    @Test
    @DisplayName("Should not create a course if the description was not sent")
    public void shouldNotCreateCourseIfDescriptionIsMissing(){

        List<String> categories = new ArrayList<>();

        categories.add("test fail");

        CourseEntity entityTest = CourseEntity.builder().name("test fail").category(categories).build();

        try{
            courseService.create(entityTest);

        } catch (Exception e) {
            Assertions.assertInstanceOf(Exception.class, e);
        }
    }

    @Test
    @DisplayName("Should return a empty list or a list with the courses")
    public void shouldReturnACourseList(){

        UUID id = UUID.randomUUID();

        List<String> categories = new ArrayList<>();

        categories.add("test");

        CourseEntity courseExample = CourseEntity.builder().name("teste").description("test").category(categories).id(id).build();

        List<CourseEntity> courseList = new ArrayList<>();

        courseList.add(courseExample);

        when(courseRepository.findAll()).thenReturn(courseList);

        var response = courseService.getAll();

        Assertions.assertNotNull(response);


    }
}
