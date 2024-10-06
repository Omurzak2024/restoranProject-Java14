package peaksoft.restoranprojectjava14.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "category_gen")
    @SequenceGenerator(name = "category_gen",
    sequenceName = "category_seq",
    allocationSize = 1)
    private Long id;
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.DETACH,
    CascadeType.MERGE, CascadeType.REFRESH})
    private List<SubCategory> subCategories;

    public Category(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public void addSubCategory(SubCategory subCategory1) {
        subCategories.add(subCategory1);
    }
}
