package com.maney.api.repository;

import com.maney.api.model.Card;
import com.maney.api.model.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Long> {

    //Pageable pageable
    List<Spending> findByDateSpendingBetweenAndCard(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate, @Param("card") Card cardId);

}
