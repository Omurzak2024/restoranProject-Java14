package peaksoft.restoranprojectjava14.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "cheque_gen")
    @SequenceGenerator(name = "cheque_gen",
    sequenceName = "cheque_seq",
    allocationSize = 1)
    private Long id;
    private int priceAverage;
    private LocalDate createdAt;
    private int grandTotal;

    @ManyToOne(cascade = {CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.REFRESH})
    private User user;

    @ManyToMany(mappedBy = "cheque", cascade = {CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.REFRESH})
    private List<MenuItem> menuItems;

    public Cheque(int priceAverage, LocalDate createdAt, int grandTotal, User user, List<MenuItem> menuItems) {
        this.priceAverage = priceAverage;
        this.createdAt = createdAt;
        this.grandTotal = grandTotal;
        this.user = user;
        this.menuItems = menuItems;
    }

    public void setUsers(User authentication){

    }
}
