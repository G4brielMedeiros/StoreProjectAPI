package dev.gabriel.storeproject.dto;

import dev.gabriel.storeproject.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
public class CategoryDTO implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    public Integer id;

    @NotEmpty(message = "Name must not be empty.")
    @Length(min = 3, max = 80, message = "Name must be between 3 and 80 characters.")
    public String name;

    public CategoryDTO(Category obj) {
        this.id = obj.getId();
        this.name = obj.getName();
    }
}
