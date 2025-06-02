package com.br.rodrigofc.more_learning_API.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseUpdateDTO {
    private Optional<String> name = Optional.empty();

    private Optional<List<String>> categories = Optional.empty();

    private Optional<String> description = Optional.empty();

    private Optional<Boolean> isActive = Optional.empty();

    public boolean isAnythingToUpdate(){
        return  name.isPresent() || categories.isPresent() || description.isPresent() || isActive.isPresent();
    }
}
