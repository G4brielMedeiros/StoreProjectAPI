package dev.gabriel.storeproject.service.entity;

import dev.gabriel.storeproject.domain.Category;
import dev.gabriel.storeproject.domain.Product;
import dev.gabriel.storeproject.dto.ProductDTO;
import dev.gabriel.storeproject.repository.CategoryRepository;
import dev.gabriel.storeproject.repository.ProductRepository;
import dev.gabriel.storeproject.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements EntityService<Product>{

    final ProductRepository repository;
    final CategoryRepository categoryRepository;

    public Product findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("" + Product.class.getSimpleName() + " not found. Id: " + id));
    }

    public Page<ProductDTO> search(
            String name,
            List<Integer> categoryIds,
            Integer page,
            Integer size,
            String orderBy,
            String direction
    ) {
        PageRequest pageRequest = PageRequest
                .of(page, size, Sort.Direction.valueOf(StringUtils.capitalize(direction)), orderBy);

        List<Category> categories = categoryRepository.findAllById(categoryIds);
        Page<Product> productPage = repository.findDistinctByNameContainingIgnoreCaseAndCategoriesIn(name, categories, pageRequest);
        return productPage.map(ProductDTO::new);

    }

}
