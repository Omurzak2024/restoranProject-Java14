package peaksoft.restoranprojectjava14.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menuItems")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "menuItem_gen")
    @SequenceGenerator(name = "menuItem_gen",
    sequenceName = "menuItem_seq",
    allocationSize = 1)
    private Long id;
    private String name;
    private String image;
    private int price;
    private String description;
    private boolean isVegetarian;
    private LocalDate isBlocked;

    @OneToOne(cascade = {CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.REFRESH})
    private StopList stopList;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private SubCategory subCategory;

    @ManyToOne(cascade = {CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.REFRESH,})
    private Restaurant restaurant;

    @ManyToMany(cascade = {CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.REFRESH,})
    private List<Cheque> cheques;

    public MenuItem(String name, String image, int price, String description, boolean isVegetarian) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.isVegetarian = isVegetarian;
    }
}
