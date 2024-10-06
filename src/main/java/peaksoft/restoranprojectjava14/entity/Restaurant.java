package peaksoft.restoranprojectjava14.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.restoranprojectjava14.enums.RestType;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "restaurant_gen")
    @SequenceGenerator(name = "restaurant_gen",
    sequenceName = "restaurant_seq",
    allocationSize = 1)
    private Long id;
    private String name;
    private String location;
    @Enumerated(EnumType.STRING)
    private RestType restType;
    private int numberOfEmployees;
    private int service;

    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.DETACH,
    CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,
    CascadeType.REMOVE})
    private List<User> users;

    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.DETACH,
    CascadeType.MERGE,CascadeType.REFRESH,})
    private List<MenuItem> menuItems;

    public void addMenuItem(MenuItem menuItem) {

    }
}
