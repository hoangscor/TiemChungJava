package edu.uth.tiemchungjava.repository;

import edu.uth.tiemchungjava.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
