package peaksoft.restoranprojectjava14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.restoranprojectjava14.entity.Cheque;

import java.time.LocalDate;


public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    @Query("select sum(c.priceAverage) from Cheque c join c.user u join u.cheques ch where u.id=:id and ch.createdAt=:date")
    int averageSumOfWaiter(@Param("id") Long id, @Param("date") LocalDate date);
}
