package in.noor.moneymanager.service;

import in.noor.moneymanager.dto.CategoryDTO;
import in.noor.moneymanager.entity.CategoryEntity;
import in.noor.moneymanager.entity.ProfileEntity;
import in.noor.moneymanager.repository.CategoryRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor


public class CategoryService {

    private final ProfileService profileService;
    private final CategoryRespository categoryRespository;

    // Save Category
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
       ProfileEntity profile =  profileService.getCurrentProfile();
        if(categoryRespository.existsByNameAndProfileId(categoryDTO.getName(), profile.getId())) {
          throw new RuntimeException("Category name already exists");
        }
        CategoryEntity newCategory = toEntity(categoryDTO, profile);
        newCategory = categoryRespository.save(newCategory);
        return toDTO(newCategory);
    }

    //get Category for Current User

    public List<CategoryDTO> getCategoriesForCurrentUser(){
        ProfileEntity profile = profileService.getCurrentProfile();
        List<CategoryEntity> categories = categoryRespository.findByProfileId(profile.getId());
        return categories.stream().map(this::toDTO).toList();
    }

// get categories By type
    public List<CategoryDTO> getCategoriesByTypeForCurrentUser(String type){
      ProfileEntity profile = profileService.getCurrentProfile();
      List <CategoryEntity> entities = categoryRespository.findByTypeAndProfileId(type, profile.getId());
     return  entities.stream().map(this::toDTO).toList();
    }

    public CategoryDTO updateCategory(Long categoryId, CategoryDTO dto) {
       ProfileEntity profile = profileService.getCurrentProfile();
      CategoryEntity existingCategory =  categoryRespository.findByIdAndProfileId(categoryId, profile.getId())
               .orElseThrow(()-> new RuntimeException("Categoty not found or not accessible"));
      existingCategory.setName(dto.getName());
      existingCategory.setIcon(dto.getIcon());
      existingCategory.setType(dto.getType()); // Added By own
      existingCategory = categoryRespository.save(existingCategory);
      return toDTO(existingCategory);
    }

    //helper Method
    private final CategoryEntity toEntity(CategoryDTO categoryDTO, ProfileEntity profile) {
     return CategoryEntity.builder()
             .name(categoryDTO.getName())
             .icon(categoryDTO.getIcon())
             .profile(profile)
             .type((categoryDTO.getType()))
             .build();
    }

    private CategoryDTO toDTO(CategoryEntity entity) {
        return CategoryDTO.builder()
                .id(entity.getId())
                .profileId(entity.getProfile() != null ? entity.getProfile().getId(): null)
                .name(entity.getName())
                .icon(entity.getIcon())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .type(entity.getType())
                .build();
    }
}
