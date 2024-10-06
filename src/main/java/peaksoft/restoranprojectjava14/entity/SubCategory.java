package peaksoft.restoranprojectjava14.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "subCategories")
@Getter
@Setter
@NoArgsConstructor
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "subCategory_gen")
    @SequenceGenerator(name = "subCategory_gen",
    sequenceName = "subCategory_seq",
    allocationSize = 1)
    private Long id;
    @NotEmpty(message = "fill in the field")
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private Category category;


    @OneToMany(mappedBy = "subcategory", cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private List<MenuItem> menuItems;

    public SubCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addMenuItem(MenuItem menuItem) {

    }
}
