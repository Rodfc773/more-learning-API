package com.br.rodrigofc.more_learning_API.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseUpdateDTO {
    private String name;

    private List<String> categories;

    private String description;

    private boolean isActive;
}
