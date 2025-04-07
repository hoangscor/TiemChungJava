package edu.uth.tiemchungjava.service;

import edu.uth.tiemchungjava.models.Category;
import edu.uth.tiemchungjava.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceIplm implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Boolean create(Category category) {
        try {
            this.categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category findById(Integer id) {
        return null;
    }

    @Override
    public Boolean update(Category category) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
