package dev.gabriel.storeproject.dto;

import dev.gabriel.storeproject.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
public class CategoryDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public Integer id;
    public String name;

    public CategoryDTO(Category obj) {
        this.id = obj.getId();
        this.name = obj.getName();
    }
}
