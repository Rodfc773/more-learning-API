package com.br.rodrigofc.more_learning_API.repositories;

import com.br.rodrigofc.more_learning_API.models.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    Optional<CourseEntity> findByName(String name);
}
