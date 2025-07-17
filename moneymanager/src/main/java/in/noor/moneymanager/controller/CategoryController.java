package in.noor.moneymanager.controller;

import in.noor.moneymanager.dto.CategoryDTO;
import in.noor.moneymanager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categorySevice;

    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategory = categorySevice.saveCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories() {
       List<CategoryDTO> categories = categorySevice.getCategoriesForCurrentUser();
       return ResponseEntity.ok(categories);
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByTypeForCurrentUser(@PathVariable String type) {
      List<CategoryDTO> list = categorySevice.getCategoriesByTypeForCurrentUser(type);
    return ResponseEntity.ok(list);
    }
@PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId
, @RequestBody CategoryDTO categoryDTO) {
      CategoryDTO updatedCategory =  categorySevice.updateCategory(categoryId, categoryDTO);
       return ResponseEntity.ok(updatedCategory);
    }

}
