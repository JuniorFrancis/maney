package com.maney.api.repository;

import com.maney.api.constants.Tag;
import com.maney.api.model.Card;
import com.maney.api.model.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Long> {

    //Pageable pageable
    List<Spending> findByDateSpendingBetweenAndCardAndUserId( @Param("startDate") LocalDate startDate,
                                                              @Param("endDate") LocalDate endDate,
                                                              @Param("card") Card cardId,
                                                              @Param("userId") Long userId );

    List<Spending> findByDateSpendingBetweenAndTag(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate, @Param("tag") Tag tag);

    Optional<Spending> findByIdAndUserId(Long id, Long userId);

    List<Spending> findByUserId(Long userId);

    //TODO Ajustar query pois não está sendo encontrado a coluna user_id
    @Query(value = "SELECT tag, amount FROM Spending WHERE user_id = ?1 AND date_spending between ?2 AND ?3 GROUP BY tag ORDER BY amount DESC LIMIT 4", nativeQuery = true)
    List<Spending> getMoreExpansiveTags(Long userId, LocalDate initialPeriod, LocalDate finalPeriod );
}
