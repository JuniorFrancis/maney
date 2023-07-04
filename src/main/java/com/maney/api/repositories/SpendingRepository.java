package com.maney.api.repositories;

import com.maney.api.constants.Tag;
import com.maney.api.models.Card;
import com.maney.api.models.Spending;
import com.maney.api.repositories.projects.ProjectTagAndAmount;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Long> {

    Page<Spending> findByDateSpendingBetweenAndCardAndUserId(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("card") Card cardId,
            @Param("userId") Long userId,
            @NotNull Pageable pageable
    );

    List<Spending> findByDateSpendingBetweenAndCardAndUserId(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("card") Card cardId,
            @Param("userId") Long userId
    );

    Page<Spending> findByDateSpendingBetweenAndTag(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("tag") Tag tag,
            Pageable pageable
    );

    Page<Spending> findByUserId(Long userId, Pageable pageable);

    Optional<Spending> findByIdAndUserId(Long id, Long userId);

    @Query(value = "SELECT tag, amount FROM Spending WHERE date_spending between ?1 AND ?2 AND user_id = ?3 GROUP BY tag ORDER BY amount DESC LIMIT 4", nativeQuery = true)
    List<ProjectTagAndAmount> getMoreExpansiveTagsByPeriod(LocalDate initialPeriod, LocalDate finalPeriod, Long userId );

    @Query(value = "SELECT tag, amount FROM Spending WHERE user_id = ?1 GROUP BY tag ORDER BY amount DESC LIMIT 4", nativeQuery = true)
    List<ProjectTagAndAmount> getMoreExpansiveTags(Long userId );
}
