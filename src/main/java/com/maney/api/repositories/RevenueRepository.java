package com.maney.api.repositories;

import com.maney.api.models.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    Optional<Revenue> findByIdAndUserId(Long id, Long userId);

    List<Revenue> findByUserId(Long userId);
}
