package my.springframework.services;

import lombok.RequiredArgsConstructor;
import my.springframework.api.v1.mapper.CategoryMapper;
import my.springframework.api.v1.model.CategoryDTO;
import my.springframework.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getByName(String name) {
        return categoryMapper.categoryToCategoryDTO(
                Optional.ofNullable(categoryRepository.findByName(name))
                        .orElseThrow(ResourceNotFoundException::new));
    }
}
