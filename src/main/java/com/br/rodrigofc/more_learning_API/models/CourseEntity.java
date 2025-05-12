package com.br.rodrigofc.more_learning_API.models;


import com.br.rodrigofc.more_learning_API.converters.StringListToArrayPostgresConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;



import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    @Convert(converter = StringListToArrayPostgresConverter.class)
    @Column(columnDefinition = "text[]")
    private String category;

    @Column(nullable = false, name = "is_active")
    private Boolean active = false;

    private UUID instructors;

    @CreationTimestamp
    private LocalDateTime created_at;

    @CreationTimestamp
    private LocalDateTime updated_at;
}
