package com.springboot.learning.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.learning.entity.primary.PrimaryDataJpaEntity;

public interface PrimaryDataJpaRepository extends JpaRepository<PrimaryDataJpaEntity, Integer> {

}
