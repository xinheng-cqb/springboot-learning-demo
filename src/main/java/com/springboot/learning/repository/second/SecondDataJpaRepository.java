package com.springboot.learning.repository.second;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.learning.entity.second.SecondDataJpaEntity;

public interface SecondDataJpaRepository extends JpaRepository<SecondDataJpaEntity, Integer> {

}
