package com.br.rodrigofc.more_learning_API.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {

    private UUID id;

    private String courseName;

    private List<String> categories;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private String description;
}
