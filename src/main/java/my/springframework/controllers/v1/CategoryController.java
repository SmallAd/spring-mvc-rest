package my.springframework.controllers.v1;

import lombok.RequiredArgsConstructor;
import my.springframework.api.v1.model.CategoryDTO;
import my.springframework.api.v1.model.CategoryListDTO;
import my.springframework.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/categories/")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories() {
        return new ResponseEntity<>(
                new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
        return new ResponseEntity<>(categoryService.getByName(name), HttpStatus.OK);
    }
}
