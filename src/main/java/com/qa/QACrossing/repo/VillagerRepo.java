package com.qa.QACrossing.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.QACrossing.entity.Villager;

@Repository
public interface VillagerRepo extends JpaRepository<Villager, Long>{

}
