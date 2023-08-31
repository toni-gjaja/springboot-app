package hr.webshop.service;

import hr.webshop.entity.Category;
import hr.webshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    @Autowired
    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> getAllCategories() {
        return repo.findAll();
    }

    public Optional<Category> getCategoryById(Long id) { return repo.findById(id); }

    public void saveCategory(Category category) { repo.save(category); }
}
