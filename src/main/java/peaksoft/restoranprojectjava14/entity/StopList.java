package peaksoft.restoranprojectjava14.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "stop_lists")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StopList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "stopList_gen")
    @SequenceGenerator(name = "stopList_gen",
    sequenceName = "stopList_seq", allocationSize = 1)
    private Long id;
    private String reason;
    private LocalDate date;

    @OneToOne(mappedBy = "stopList", cascade = {CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.REFRESH,
    CascadeType.REMOVE})
    private MenuItem menuItem;

    public StopList(String reason, LocalDate date) {
        this.reason = reason;
        this.date = date;
    }
}
